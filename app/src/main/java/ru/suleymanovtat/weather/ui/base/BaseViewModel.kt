package ru.suleymanovtat.weather.ui.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import com.google.gson.JsonSyntaxException
import io.reactivex.exceptions.CompositeException
import retrofit2.HttpException
import ru.suleymanovtat.weather.App
import ru.suleymanovtat.weather.R
import java.net.UnknownHostException

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private fun getContext() = getApplication<App>()
    protected fun getString(@StringRes id: Int): String = getContext().getString(id)

    protected fun onError(throwable: Throwable): String {
        var localThrowable = throwable
        if (localThrowable is CompositeException) {
            localThrowable = localThrowable.exceptions.first()
        }
        return when (localThrowable) {
            is UnknownHostException -> getString(R.string.no_internet_connection)
            is HttpException -> when {
                localThrowable.code() >= 500 -> getString(R.string.server_error)
                localThrowable.code() == 400 -> getString(R.string.unknown_error_has_occurred)
                localThrowable.code() == 404 -> getString(R.string.сould_not_find)
                else -> getString(R.string.unknown_error_has_occurred)
            }
            is JsonSyntaxException -> {
                getString(R.string.impossible_parse_answer)
            }
            else -> getString(R.string.unknown_error_has_occurred)
        }
    }
}