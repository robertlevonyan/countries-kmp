package com.robertlevonyan.countrieskmp.di

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual fun <T : HttpClientEngineConfig> getHttpClientEngineFactory(): HttpClientEngineFactory<T> =
    CIO as HttpClientEngineFactory<T>