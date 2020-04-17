package ru.suleymanovtat.weather.repository

import ru.suleymanovtat.weather.BuildConfig
import ru.suleymanovtat.weather.model.Response
import ru.suleymanovtat.weather.repository.network.ServerCommunicator

class WeatherRepository(private val serverCommunicator: ServerCommunicator) {

    suspend fun getWeather(lat: String, lng: String): UseCaseResult<Response> {
        return try {
            val result = serverCommunicator.getWeather(lat, lng, BuildConfig.API_KEY)
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}