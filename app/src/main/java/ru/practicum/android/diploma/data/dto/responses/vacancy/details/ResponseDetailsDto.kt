package ru.practicum.android.diploma.data.dto.responses.vacancy.details

import android.text.Html
import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.responses.vacancy.Area
import ru.practicum.android.diploma.data.dto.responses.vacancy.Employer
import ru.practicum.android.diploma.data.dto.responses.vacancy.Salary
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails

private const val EMPTY_SALARY = "Зарплата не указана"

data class ResponseDetailsDto(
    val id: String, // id вакансии
    val name: String, // название вакансии
    val salary: Salary? = null, // внутри з/п
    val address: Address?,
    @SerializedName("alternate_url")
    val alternateUrl: String, // ссылка на вакансию
    val area: Area, // внутри название регион
    val contacts: Contacts, // контакты: имя, маил, коментарий, телефоны
    val description: String, // описание вакансии
    val employer: Employer, // внутри данные компании
    val experience: Experience, // внутри строка для заполнения поля опыт
    @SerializedName("key_skills")
    val keySkills: List<KeySkill>, // основные навыки
    @SerializedName(" professional_roles")
    val professionalRoles: List<ProfessionalRole>, // внури названия профессиональных ролей
    @SerializedName("published_at")
    val publishedAt: String, // дата и время публикации вакансии в формате "2013-07-08T16:17:21+0400"
)

fun ResponseDetailsDto.mapToVacancyDetails(): VacancyDetails {
    return VacancyDetails(
        vacancyId = this.id,
        vacancyName = "${this.name}, ",
        salary = getSalaryAsStr(this.salary),
        experience = this.experience.name,
        keySkills = this.keySkills.map { it.toString() },
        vacancyDescription = "${Html.fromHtml(this.description, Html.FROM_HTML_MODE_COMPACT)}",
        companyName = this.employer.name,
        companyLogoLittle = this.employer.logoUrls?.little ?: "",
        companyLogoMedium = this.employer.logoUrls?.medium ?: "",
        comment = getCommit(this.contacts.phones),
        email = this.contacts.email,
        managerName = this.contacts.name,
        phones = getPhones(this.contacts.phones),
        address = getAddress(this.address),
        city = this.area.name
    )
}

fun getSalaryAsStr(salary: Salary?): String {
    if (salary == null) return EMPTY_SALARY
    val resultStr: StringBuilder = StringBuilder("")
    resultStr.append("от ${salary.from}")
    if (salary.to != EMPTY_PARAM_NUM) resultStr.append("до ${salary.to}")
    resultStr.append(" ${salary.currency}")
    return resultStr.toString()
}

private fun getCommit(phones: List<Phone>): String {
    if (phones.isEmpty()) return EMPTY_PARAM_SRT
    val resultStr: StringBuilder = StringBuilder("")
    phones.forEach { if (it.comment != EMPTY_PARAM_SRT) resultStr.append(it.comment) }
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
    if (address == null) return EMPTY_PARAM_SRT
    val resultStr = StringBuilder("")
    resultStr.append("${address.city}, ").append("${address.street}, ").append(address.building)
    return resultStr.toString()
}
