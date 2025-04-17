package com.mantisbayne.weatherrunts.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @SerialName("generationtime_ms") val generationTimeMs: Double,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int,
    val timezone: String,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerialName("hourly_units") val hourlyUnits: HourlyUnits,
    val hourly: HourlyData
)

@Serializable
data class HourlyUnits(
    val time: String,
    @SerialName("precipitation_probability") val precipitationProbability: String,
    @SerialName("apparent_temperature") val apparentTemperature: String,
    @SerialName("temperature_2m") val temperature2m: String
)

@Serializable
data class HourlyData(
    val time: List<String>,
    @SerialName("precipitation_probability") val precipitationProbability: List<Int>,
    @SerialName("apparent_temperature") val apparentTemperature: List<Double>,
    @SerialName("temperature_2m") val temperature2m: List<Double>
)
