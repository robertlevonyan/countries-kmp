package com.robertlevonyan.countrieskmp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
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
        HttpClient(CIO) {
            engine {
                requestTimeout = 100_000
            }
            install(ContentNegotiation) {
                json(get())
            }
//            expectSuccess = true
//            HttpResponseValidator {
//                handleResponseExceptionWithRequest { exception, request ->
//                    val clientException =
//                        exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
//                    val exceptionResponse = clientException.response
//                    if (exceptionResponse.status == HttpStatusCode.NotFound) {
//                        throw A2bException(ExceptionType.API, -1, clientException)
//                    }
//                }
//            }
        }
    }
}