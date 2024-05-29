package com.robertlevonyan.countrieskmp.repository

import com.robertlevon.countrieskmp.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun getCountries(
        input: String = "",
        region: String = "",
    ): Flow<Map<String, List<Country>>>

    suspend fun getRegions(): List<String>

    suspend fun getCountry(cca2: String): Country?
}
