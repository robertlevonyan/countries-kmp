package com.robertlevonyan.countrieskmp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single {
        HttpClient(getHttpClientEngineFactory()) {
            install(ContentNegotiation) {
                json(get())
            }
        }
    }
}

expect fun <T : HttpClientEngineConfig> getHttpClientEngineFactory(): HttpClientEngineFactory<T>
