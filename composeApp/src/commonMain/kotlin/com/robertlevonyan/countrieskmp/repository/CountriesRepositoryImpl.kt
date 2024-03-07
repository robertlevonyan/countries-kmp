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
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class CountriesRepositoryImpl(
    private val httpClient: HttpClient,
    private val json: Json,
) : CountriesRepository {
    override suspend fun getCountries(): List<Country> = try {
        val httpResponse = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "restcountries.com"
                path("v3.1", "all")
                contentType(ContentType.Application.Json)
            }
        }
        httpResponse.body<String?>()?.let { jsonString ->
            println(jsonString)
            json.decodeFromString(ListSerializer(Country.serializer()), jsonString)
        } ?: emptyList()
    } catch (e: Exception) {
        println("C $e")
        emptyList()
    }
}
