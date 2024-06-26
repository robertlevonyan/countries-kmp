package com.robertlevonyan.countrieskmp.repository

import app.cash.sqldelight.coroutines.asFlow
import com.robertlevon.countrieskmp.CountriesQueries
import com.robertlevon.countrieskmp.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import com.robertlevonyan.countrieskmp.entity.Country as RemoteCountry

class CountriesRepositoryImpl(
    private val httpClient: HttpClient,
    private val json: Json,
    private val countriesQueries: CountriesQueries,
) : CountriesRepository {
    override suspend fun getCountries(
        input: String,
        region: String,
    ): Flow<Map<String, List<Country>>> = try {
        countriesQueries.createTableIfNotExists()
        countriesQueries.createUniqueIndexIfNotExists()
        countriesQueries.selectAllCountries().asFlow()
            .map { query -> query.executeAsList() }
            .onEach { countries ->
                countries.ifEmpty {
                    val httpResponse = httpClient.get {
                        url {
                            protocol = URLProtocol.HTTPS
                            host = "restcountries.com"
                            path("v3.1", "all")
                            contentType(ContentType.Application.Json)
                        }
                    }
                    val remoteCountries = httpResponse.body<String?>()?.let { jsonString ->
                        json.decodeFromString(
                            ListSerializer(RemoteCountry.serializer()),
                            jsonString
                        )
                    } ?: emptyList()
                    val localCountries = remoteCountries.map { remoteCountry ->
                        Country(
                            commonName = remoteCountry.name?.common,
                            officialName = remoteCountry.name?.official,
                            nativeName = "",
                            currencies = remoteCountry.currencies?.values?.joinToString(", ") { currency ->
                                "${currency.name} ${currency.symbol}"
                            },
                            capital = remoteCountry.capital?.firstOrNull(),
                            region = remoteCountry.region,
                            subregion = remoteCountry.subregion,
                            languages = remoteCountry.languages?.values?.joinToString(", "),
                            area = remoteCountry.area,
                            flag = remoteCountry.flag,
                            population = remoteCountry.population,
                            countryFlag = remoteCountry.flags?.getOrElse("png") { "" },
                            coatOfArms = remoteCountry.coatOfArms?.getOrElse("png") { "" },
                            cca2 = remoteCountry.cca2.orEmpty(),
                        )
                    }

                    countriesQueries.transaction {
                        localCountries.forEach { localCountry ->
                            countriesQueries.insertCountry(localCountry)
                        }
                    }
                    localCountries
                }
            }
            .map { allCountries ->
                allCountries.filter { country ->
                    country.commonName?.contains(
                        other = input,
                        ignoreCase = true,
                    ) == true && country.region?.contains(
                        other = if (region == REGION_ALL) "" else region,
                        ignoreCase = true,
                    ) == true
                }.groupBy { country -> country.commonName?.first().toString() }
            }
    } catch (e: Exception) {
        emptyFlow()
    }.flowOn(Dispatchers.IO)

    override suspend fun getRegions(): List<String> = withContext(Dispatchers.IO) {
        buildList {
            add(REGION_ALL)
            addAll(
                countriesQueries.selectAllCountries().executeAsList()
                    .groupBy { it.region }.keys.toList().filterNotNull()
            )
        }
    }

    override suspend fun getCountry(cca2: String): Country? = withContext(Dispatchers.IO) {
        countriesQueries.selectCountryById(cca2).executeAsOneOrNull()
    }

    companion object {
        const val REGION_ALL = "All"
    }
}
