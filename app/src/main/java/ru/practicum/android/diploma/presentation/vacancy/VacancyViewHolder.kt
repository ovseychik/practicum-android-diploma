package ru.practicum.android.diploma.presentation.vacancy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

class VacancyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ivLogo: ImageView = itemView.findViewById(R.id.iv_company_logo)
    private val tvVacancyTitle: TextView = itemView.findViewById(R.id.tv_vacancy_title)
    private val tvCompanyName: TextView = itemView.findViewById(R.id.tv_company_name)
    private val tvSalary: TextView = itemView.findViewById(R.id.tv_salary)

    fun bind(vacancyItem: VacancyItem) {
        tvVacancyTitle.text = vacancyItem.nameVacancy
        tvCompanyName.text = vacancyItem.nameCompany
        tvSalary.text = vacancyItem.salary

        Glide.with(itemView)
            .load(vacancyItem.logo)
            .centerCrop()
            .placeholder(R.drawable.ic_vacancy_logo_placeholder)
            .into(ivLogo)

    }
}
