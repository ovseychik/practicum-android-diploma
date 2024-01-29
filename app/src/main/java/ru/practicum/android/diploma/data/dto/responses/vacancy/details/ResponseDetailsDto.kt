package ru.practicum.android.diploma.data.dto.responses.vacancy.details

import android.text.Html
import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.responses.vacancy.Area
import ru.practicum.android.diploma.data.dto.responses.vacancy.Employer
import ru.practicum.android.diploma.data.dto.responses.vacancy.Salary
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.LogoUrls
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.util.getCurrencySymbol
import java.text.DecimalFormat
import java.util.Locale

private const val EMPTY_SALARY = "Зарплата не указана"

data class ResponseDetailsDto(
    val id: String, // id вакансии
    val name: String, // название вакансии
    val salary: Salary? = null, // внутри з/п
    val address: Address?,
    @SerializedName("alternate_url")
    val alternateUrl: String, // ссылка на вакансию
    val area: Area, // внутри название регион
    val contacts: Contacts?, // контакты: имя, маил, коментарий, телефоны
    val description: String, // описание вакансии
    val employer: Employer, // внутри данные компании
    val experience: Experience?, // внутри строка для заполнения поля опыт
    @SerializedName("key_skills")
    val keySkills: List<KeySkill>, // основные навыки
    @SerializedName(" professional_roles")
    val professionalRoles: List<ProfessionalRole>, // внури названия профессиональных ролей
    @SerializedName("published_at")
    val publishedAt: String, // дата и время публикации вакансии в формате "2013-07-08T16:17:21+0400"
    val employment: Employment?, // тип занятости (полный день)
    val schedule: Schedule?, // график работы (удаленка)

)

fun ResponseDetailsDto.mapToVacancyDetails(): VacancyDetails {
    return VacancyDetails(
        vacancyId = this.id,
        vacancyName = "${this.name}",
        salary = getSalaryAsStr(this.salary),
        experience = this.experience?.name ?: EMPTY_PARAM_SRT,
        keySkills = this.keySkills.map { it.name },
        vacancyDescription = "${Html.fromHtml(this.description, Html.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING)}",
        companyName = this.employer.name,
        companyLogoLittle = getLogoUrl(this.employer.logoUrls),
        companyLogoMedium = this.employer.logoUrls?.medium ?: "",
        comment = getComment(this.contacts?.phones ?: emptyList()),
        email = this.contacts?.email ?: EMPTY_PARAM_SRT,
        managerName = this.contacts?.name ?: EMPTY_PARAM_SRT,
        phones = getPhones(this.contacts?.phones ?: emptyList()),
        address = getAddress(this.address),
        city = this.area.name,
        schedule = this.schedule?.name ?: EMPTY_PARAM_SRT,
        employment = this.employment?.name ?: EMPTY_PARAM_SRT,
        alternateUrl = this.alternateUrl
    )
}

private fun getLogoUrl(logoUrls: LogoUrls?): String {
    if (logoUrls == null) return ""
    var result = ""
    if (logoUrls.medium != null) {
        result = logoUrls.medium
    } else if (logoUrls.little != null) {
        result = logoUrls.little
    } else {
        result = logoUrls.original
    }
    return result
}

fun getSalaryAsStr(salary: Salary?): String {
    if (salary == null) return EMPTY_SALARY
    val resultStr: StringBuilder = StringBuilder("")
    resultStr.append("от ${formatSalary(salary.from)}")
    if (salary.to != null) resultStr.append(" до ${formatSalary(salary.to)}")
    resultStr.append(" ${getCurrencySymbol(salary.currency)}")
    return resultStr.toString()
}

private fun formatSalary(salary: Int): String {
    val format = DecimalFormat.getNumberInstance(Locale("ru", "RU"))
    return format.format(salary)
}

private fun getComment(phones: List<Phone>): String {
    if (phones.isEmpty()) return EMPTY_PARAM_SRT
    val resultStr: StringBuilder = StringBuilder("")
    phones.forEach { if (it.comment != null) resultStr.append(it.comment) }
    return resultStr.toString()
}

fun getPhones(phones: List<Phone>): List<String> {
    if (phones.isEmpty()) return emptyList()
    val resultStr = mutableListOf<String>()
    phones.forEach {
        val phoneStr = StringBuilder("")
        phoneStr.append("+${it.country} (${it.city}) ${it.number}")
        resultStr.add(phoneStr.toString())
    }
    return resultStr
}

private fun getAddress(address: Address?): String {
    if (address?.city == null || address.street == null || address.building == null) return EMPTY_PARAM_SRT
    val resultStr = StringBuilder("")
    resultStr.append("${address.city}, ").append("${address.street}, ").append(address.building)
    return resultStr.toString()
}
