package com.robertlevonyan.countrieskmp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val networkModule = DI.Module("networkModule") {
    bindSingleton {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    bindSingleton {
        HttpClient(getHttpClientEngineFactory()) {
            install(ContentNegotiation) {
                json(instance())
            }
        }
    }
}

expect fun <T : HttpClientEngineConfig> getHttpClientEngineFactory(): HttpClientEngineFactory<T>