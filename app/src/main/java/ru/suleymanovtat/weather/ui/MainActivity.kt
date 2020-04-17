package ru.suleymanovtat.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.suleymanovtat.weather.R
import ru.suleymanovtat.weather.ui.weathers.CitiesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, CitiesFragment.newInstance()).commit()
        }
    }
}
