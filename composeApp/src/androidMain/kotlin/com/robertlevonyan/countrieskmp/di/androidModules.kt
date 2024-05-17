package com.robertlevonyan.countrieskmp.di

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.robertlevon.countrieskmp.AppDatabase
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun <T : HttpClientEngineConfig> getHttpClientEngineFactory(): HttpClientEngineFactory<T> =
    OkHttp as HttpClientEngineFactory<T>

actual class DatabaseDriverFactory actual constructor() {
    private lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
    }

    actual fun provideDbDriver(): SqlDriver =
        AndroidSqliteDriver(AppDatabase.Schema.synchronous(), context, "Countries.db")
}

