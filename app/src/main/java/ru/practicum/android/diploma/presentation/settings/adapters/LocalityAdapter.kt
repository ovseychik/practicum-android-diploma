package ru.practicum.android.diploma.presentation.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ViewCountryItemBinding
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.presentation.settings.viewholders.LocalityViewHolder

class LocalityAdapter(private val onClick: (LocalityAdapterItem) -> Unit) :
    RecyclerView.Adapter<LocalityViewHolder>() {
    private val items: MutableList<LocalityAdapterItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalityViewHolder {
        val binding = ViewCountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocalityViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LocalityViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick.invoke(item) }
    }

    fun addPlaces(places: List<PlaceItem>) {
        places.forEach {
            items.add(LocalityAdapterItem.City(it))
        }
        notifyItemRangeInserted(items.size - places.size, places.size)
    }

    fun addCountries(countries: List<Country>) {
        countries.forEach {
            items.add(LocalityAdapterItem.CountryItem(it))
        }
        notifyDataSetChanged()
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

}
