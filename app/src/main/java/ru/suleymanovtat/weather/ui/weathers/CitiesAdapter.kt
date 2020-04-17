package ru.suleymanovtat.weather.ui.weathers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_city.view.*
import ru.suleymanovtat.weather.R
import ru.suleymanovtat.weather.model.City

class CitiesAdapter(private var list: MutableList<City>? = null) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = list?.get(position)
        with(holder.mView) {
            city?.let { it ->
                textViewCityName.text = it.name
                textViewTemp.text =
                    context.getString(
                        R.string.celsius,
                        it.main?.temp.toString()
                    )
                textViewDescription.text = context.getString(
                    R.string.description, it.main?.temp.toString()
                )
                imageViewIcon.load(it.weather?.first()?.icon)
                imageViewDelete.setOnClickListener {
                    list?.let {
                        it.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, it.size)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
    )

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

    override fun getItemCount() = list?.size ?: 0
}