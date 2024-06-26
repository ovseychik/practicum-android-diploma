package ru.practicum.android.diploma.presentation.vacancy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem
import ru.practicum.android.diploma.util.dpToPx

private const val RADIUS_ICON_DP = 12.0f

class VacancyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ivLogo: ImageView = itemView.findViewById(R.id.iv_company_logo)
    private val tvVacancyTitle: TextView = itemView.findViewById(R.id.tv_vacancy_title)
    private val tvCompanyName: TextView = itemView.findViewById(R.id.tv_company_name)
    private val tvSalary: TextView = itemView.findViewById(R.id.tv_salary)
    private val radiusIconVacancyPx = dpToPx(RADIUS_ICON_DP, itemView.context)

    fun bind(vacancyItem: VacancyItem) {
        tvVacancyTitle.text =
            itemView.context.getString(R.string.employer_title_plus_city, vacancyItem.nameVacancy, vacancyItem.city)
        tvCompanyName.text = vacancyItem.nameCompany
        tvSalary.text = vacancyItem.salary

        Glide.with(itemView)
            .load(vacancyItem.logo)
            .transform(FitCenter(), RoundedCorners(radiusIconVacancyPx))
            .placeholder(R.drawable.ic_vacancy_logo_placeholder)
            .into(ivLogo)
    }
}
