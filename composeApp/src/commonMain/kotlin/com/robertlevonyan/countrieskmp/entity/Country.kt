package com.robertlevonyan.countrieskmp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("name")
    val name: CountryName,
    @SerialName("tld")
    val topLevelDomain: List<String>,
    @SerialName("ccn3")
    val cellularNetworkFactor: String,
    @SerialName("status")
    val status: String,
    @SerialName("unMember")
    val unMember: Boolean,
    @SerialName("currencies")
    val currencies: Map<String, CountryCurrency>,
    @SerialName("capital")
    val capital: List<String>,
    @SerialName("altSpellings")
    val altSpellings: List<String>,
    @SerialName("region")
    val region: String,
    @SerialName("subregion")
    val subregion: String,
    @SerialName("languages")
    val languages: Map<String, String>,
    @SerialName("translations")
    val translations: Map<String, CountryName>,
    @SerialName("latlng")
    val latlng: List<Double>,
    @SerialName("landlocked")
    val landlocked: Boolean,
    @SerialName("borders")
    val borders: List<String>,
    @SerialName("area")
    val area: Double,
    @SerialName("flag")
    val flag: String,
    @SerialName("maps")
    val maps: Map<String, String>,
    @SerialName("population")
    val population: Long,
    @SerialName("fifa")
    val fifa: String,
    @SerialName("timezones")
    val timezones: List<String>,
    @SerialName("continents")
    val continents: List<String>,
    @SerialName("flags")
    val flags: Map<String, String>,
    @SerialName("coatOfArms")
    val coatOfArms: Map<String, String>,
    @SerialName("startOfWeek")
    val startOfWeek: String,
    @SerialName("capitalInfo")
    val capitalInfo: Map<String, Double>,
    @SerialName("postalCode")
    val postalCode: Map<String, String>,
)

@Serializable
data class CountryName(
    @SerialName("common")
    val common: String,
    @SerialName("official")
    val official: String,
    @SerialName("nativeName")
    val nativeName: Map<String, CountryNativeName>,
)

@Serializable
data class CountryNativeName(
    @SerialName("common")
    val common: String,
    @SerialName("official")
    val official: String,
)

@Serializable
data class CountryCurrency(
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
)
