package com.robertlevonyan.countrieskmp.repository

import com.robertlevonyan.countrieskmp.entity.Country

interface CountriesRepository {
    suspend fun getCountries(): List<Country>
}