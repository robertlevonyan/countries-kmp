package com.robertlevonyan.countrieskmp.di

import io.ktor.client.engine.HttpClientEngine

actual fun getClient(): HttpClientEngine = Android