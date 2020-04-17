package ru.suleymanovtat.weather.ui.weathers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_cities.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.suleymanovtat.weather.R
import ru.suleymanovtat.weather.ui.utils.hideKeyboard


class CitiesFragment : Fragment(R.layout.fragment_cities) {

    companion object {
        fun newInstance() = CitiesFragment()
    }

    private val viewModel by viewModel<CitiesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSend.setOnClickListener {
            viewModel.sendCoordinates(
                editTextLat.text.toString(),
                editTextLng.text.toString()
            )
            hideKeyboard()
        }
        viewModel.message.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                texViewMessage.text = message
                recyclerViewCities.visibility = View.GONE
                texViewMessage.visibility = View.VISIBLE
            }
        })
        viewModel.cities.observe(viewLifecycleOwner, Observer { cities ->
            cities?.let {
                recyclerViewCities.visibility = View.VISIBLE
                texViewMessage.visibility = View.GONE
                val adapter = CitiesAdapter(cities)
                recyclerViewCities.adapter = adapter
            }
        })
    }
}
