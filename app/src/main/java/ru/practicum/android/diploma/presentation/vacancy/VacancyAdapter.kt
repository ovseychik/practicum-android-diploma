package ru.practicum.android.diploma.presentation.vacancy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

class VacancyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<ItemType> = mutableListOf()

    fun addVacancy() {
        items.add(ItemType.VACANCY)
        notifyItemInserted(items.size - 1)
    }

    fun addLoading() {
        items.add(ItemType.LOADING)
        notifyItemInserted(items.size - 1)
    }

    fun removeLoading() {
        val position = items.indexOf(ItemType.LOADING)
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            ItemType.VACANCY -> 0
            ItemType.LOADING -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> VacancyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_vacancy, parent, false)
            )

            else -> LoadingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_vacancy, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> (holder as VacancyViewHolder).bind(items[position] as VacancyItem)
            1 -> (holder as LoadingViewHolder).bind()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
