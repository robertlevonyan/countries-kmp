package com.robertlevonyan.countrieskmp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import java.io.File

actual fun <T : HttpClientEngineConfig> getHttpClientEngineFactory(): HttpClientEngineFactory<T> =
    CIO as HttpClientEngineFactory<T>

actual class DatabaseDriverFactory {
    actual fun provideDbDriver(): SqlDriver {
        val parentFolder = File(System.getProperty("user.home") + "/CountriesKmp")
        if (!parentFolder.exists()) {
            parentFolder.mkdirs()
        }
        val databasePath = File(parentFolder, "Countries.db")

        return JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
    }
}
