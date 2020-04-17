package ru.suleymanovtat.weather.repository.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.suleymanovtat.weather.model.Response

interface ApiService {

    @GET("find?cnt=10&units=metric&lang=ru")
    suspend fun getWeather(
        @Query("lat") lat: String, @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Response
}
