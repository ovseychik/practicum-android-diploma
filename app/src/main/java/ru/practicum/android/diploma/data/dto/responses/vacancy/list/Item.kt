package ru.practicum.android.diploma.data.dto.responses.vacancy.list

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.responses.vacancy.Area
import ru.practicum.android.diploma.data.dto.responses.vacancy.Employer
import ru.practicum.android.diploma.data.dto.responses.vacancy.Salary
import ru.practicum.android.diploma.data.dto.responses.vacancy.details.getSalaryAsStr
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

data class Item(
    val id: String, // идентификатор вакансии
    val area: Area, // внутри название город
    val employer: Employer, // внутри информация об организации и лого
    val name: String, // название вакансии
    @SerializedName("published_at")
    val publishedAt: String, // дата публикации в формате "2013-10-11T13:27:16+0400"
    val salary: Salary, // внутри з/п
    @SerializedName("show_logo_in_search")
    val showLogo: Boolean, // показывать ли логотип при поиске
)

fun Item.mapToVacancyItem(): VacancyItem {
    return VacancyItem(
        id = this.id,
        city = this.area.name,
        logo = this.employer.logoUrls?.little ?: EMPTY_PARAM_SRT,
        nameVacancy = this.name,
        nameCompany = this.employer.name,
        salary = getSalaryAsStr(this.salary)
    )
}
