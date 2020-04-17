package ru.suleymanovtat.weather.model

class Response(
    val cnt: Int? = null,
    val list: List<City>? = null
)

class City(
    val name: String? = null,
    var weather: List<Weather>? = null,
    val main: Main? = null
)

class Weather(
    val id: Int? = null,
    val main: String? = null,
    val description: String? = null,
    var icon: String? = null
)

class Main(val temp: Double? = null)
