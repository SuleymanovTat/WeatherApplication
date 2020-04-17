package ru.suleymanovtat.weather.ui.weathers

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.suleymanovtat.weather.R
import ru.suleymanovtat.weather.model.City
import ru.suleymanovtat.weather.module.API_ICON
import ru.suleymanovtat.weather.repository.UseCaseResult
import ru.suleymanovtat.weather.repository.WeatherRepository
import ru.suleymanovtat.weather.ui.base.BaseViewModel

class CitiesViewModel(
    application: Application,
    private val repository: WeatherRepository
) : BaseViewModel(application) {

    val cities = MutableLiveData<MutableList<City>>()
    val message = MutableLiveData<String>()

    fun sendCoordinates(lat: String, lng: String) {
        cities.postValue(null)
        message.postValue(null)
        if (lat.trim().isEmpty()) {
            message.postValue(getString(R.string.enter_the_correct_latitude))
            return
        }
        if (lng.trim().isEmpty()) {
            message.postValue(getString(R.string.enter_the_correct_longitude))
            return
        }
        if (!isValidLatLng(lat.toDouble(), lng.toDouble())) {
            message.postValue(getString(R.string.invalid_latitude_or_longitude))
            return
        }
        getWeather(lat, lng)
    }

    private fun getWeather(lat: String, lng: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getWeather(lat, lng)) {
                is UseCaseResult.Success -> {
                    result.data.list?.let {
                        val list = iconWeather(it)
                        cities.postValue(list as MutableList<City>?)
                    }
                }
                is UseCaseResult.Error -> message.postValue(onError(result.exception))
            }
        }
    }

    private fun iconWeather(it: List<City>): List<City> {
        return it.map {
            it.weather?.first()?.let { weather ->
                weather.icon.let { icon ->
                    weather.icon = "${API_ICON + icon}@2x.png"
                }
            }
            it
        }
    }

    private fun isValidLatLng(lat: Double, lng: Double): Boolean {
        if (lat < -90 || lat > 90) {
            return false
        } else if (lng < -180 || lng > 180) {
            return false
        }
        return true
    }
}