package ru.practicum.android.diploma.presentation.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ViewCountryItemBinding
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.presentation.settings.viewholders.LocationViewHolder

class LocationAdapter(private val onClick: (LocationAdapterItem) -> Unit) :
    RecyclerView.Adapter<LocationViewHolder>() {
    private val items: MutableList<LocationAdapterItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ViewCountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick.invoke(item) }
    }

    fun addPlaces(places: List<PlaceItem>) {
        places.forEach {
            items.add(LocationAdapterItem.City(it))
        }
        notifyItemRangeInserted(items.size - places.size, places.size)
    }

    fun addCountries(countries: List<Country>) {
        countries.forEach {
            items.add(LocationAdapterItem.CountryItem(it))
        }
        notifyItemRangeInserted(items.size - countries.size, countries.size)
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

}
