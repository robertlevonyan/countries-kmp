package com.robertlevonyan.countrieskmp.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.robertlevon.countrieskmp.AppDatabase
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun <T : HttpClientEngineConfig> getHttpClientEngineFactory(): HttpClientEngineFactory<T> =
    Darwin as HttpClientEngineFactory<T>

actual class DatabaseDriverFactory {
    actual fun provideDbDriver(): SqlDriver =
        NativeSqliteDriver(AppDatabase.Schema.synchronous(), "Countries.db")
}
