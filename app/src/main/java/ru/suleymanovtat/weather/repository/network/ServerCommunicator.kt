package ru.suleymanovtat.weather.repository.network

import ru.suleymanovtat.weather.model.Response

class ServerCommunicator(private val apiService: ApiService) {

    suspend fun getWeather(lat: String, lng: String, apiKey: String): Response {
        return apiService.getWeather(lat, lng, apiKey)
    }
}
