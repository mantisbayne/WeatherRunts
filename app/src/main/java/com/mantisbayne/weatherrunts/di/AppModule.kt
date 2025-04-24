package com.mantisbayne.weatherrunts.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mantisbayne.weatherrunts.data.api.WeatherApi
import com.mantisbayne.weatherrunts.data.repository.WeatherRepository
import com.mantisbayne.weatherrunts.domain.reducer.WeatherDomainStateReducer
import com.mantisbayne.weatherrunts.domain.usecase.WeatherUseCase
import com.mantisbayne.weatherrunts.utils.DateFormatter
import com.mantisbayne.weatherrunts.utils.TimeOfDayMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepository(weatherApi)
    }

    @Provides
    @Singleton
    fun provideDateFormatter(): DateFormatter {
        return DateFormatter()
    }

    @Provides
    @Singleton
    fun provideTimeOfDayMapper(dateFormatter: DateFormatter): TimeOfDayMapper {
        return TimeOfDayMapper(dateFormatter)
    }

    @Provides
    @Singleton
    fun provideWeatherDomainStateReducer(timeOfDayMapper: TimeOfDayMapper): WeatherDomainStateReducer {
        return WeatherDomainStateReducer(timeOfDayMapper)
    }

    @Provides
    @Singleton
    fun provideWeatherUseCase(
        domainStateReducer: WeatherDomainStateReducer,
        repository: WeatherRepository
    ): WeatherUseCase {
        return WeatherUseCase(domainStateReducer, repository)
    }
}