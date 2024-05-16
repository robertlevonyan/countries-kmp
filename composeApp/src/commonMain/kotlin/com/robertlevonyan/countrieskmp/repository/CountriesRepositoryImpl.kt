package com.robertlevonyan.countrieskmp.repository

import com.robertlevonyan.countrieskmp.entity.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class CountriesRepositoryImpl(
    private val httpClient: HttpClient,
    private val json: Json,
) : CountriesRepository {
    override suspend fun getCountries(): Map<String, List<Country>> = try {
        val httpResponse = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "restcountries.com"
                path("v3.1", "all")
                contentType(ContentType.Application.Json)
            }
        }
        val countries = httpResponse.body<String?>()?.let { jsonString ->
            json.decodeFromString(ListSerializer(Country.serializer()), jsonString)
        } ?: emptyList()

        countries
            .asSequence()
            .sortedBy { it.name?.common }
            .groupBy { it.name?.common?.first().toString() }
    } catch (e: Exception) {
        emptyMap()
    }
}
