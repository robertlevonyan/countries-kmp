package com.robertlevonyan.countrieskmp.entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("name")
    val name: CountryName? = null,
    @SerialName("tld")
    @Contextual
    val topLevelDomain: List<String>? = null,
    @SerialName("cca2")
    val cca2: String? = null,
    @SerialName("ccn3")
    val cellularNetworkFactor: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("unMember")
    val unMember: Boolean? = null,
    @SerialName("currencies")
    val currencies: Map<String, CountryCurrency>? = null,
    @SerialName("capital")
    val capital: List<String>? = null,
    @SerialName("altSpellings")
    val altSpellings: List<String>? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("subregion")
    val subregion: String? = null,
    @SerialName("languages")
    val languages: Map<String, String>? = null,
    @SerialName("translations")
    val translations: Map<String, CountryName>? = null,
    @SerialName("latlng")
    val latlng: List<Double>? = null,
    @SerialName("landlocked")
    val landlocked: Boolean? = null,
    @SerialName("borders")
    val borders: List<String>? = null,
    @SerialName("area")
    val area: Double? = null,
    @SerialName("flag")
    val flag: String? = null,
    @SerialName("maps")
    val maps: Map<String, String>? = null,
    @SerialName("population")
    val population: Long? = null,
    @SerialName("fifa")
    val fifa: String? = null,
    @SerialName("timezones")
    val timezones: List<String>? = null,
    @SerialName("continents")
    val continents: List<String>? = null,
    @SerialName("flags")
    val flags: Map<String, String>? = null,
    @SerialName("coatOfArms")
    val coatOfArms: Map<String, String>? = null,
    @SerialName("startOfWeek")
    val startOfWeek: String? = null,
    @SerialName("capitalInfo")
    val capitalInfo: CapitalInfo? = null,
    @SerialName("postalCode")
    val postalCode: Map<String, String>? = null,
)

@Serializable
data class CountryName(
    @SerialName("common")
    val common: String? = null,
    @SerialName("official")
    val official: String? = null,
    @SerialName("nativeName")
    val nativeName: Map<String, CountryNativeName>? = null,
)

@Serializable
data class CountryNativeName(
    @SerialName("official")
    val official: String? = null,
    @SerialName("common")
    val common: String? = null,
)

@Serializable
data class CountryCurrency(
    @SerialName("name")
    val name: String? = null,
    @SerialName("symbol")
    val symbol: String? = null,
)

@Serializable
data class CapitalInfo(
    @SerialName("latlng")
    val latlng: List<Double>? = null,
)
