package com.robertlevonyan.countrieskmp.di

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun <T : HttpClientEngineConfig> getHttpClientEngineFactory(): HttpClientEngineFactory<T> =
    OkHttp as HttpClientEngineFactory<T>