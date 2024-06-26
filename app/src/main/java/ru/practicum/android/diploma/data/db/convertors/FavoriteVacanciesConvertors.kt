package ru.practicum.android.diploma.data.db.convertors

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.practicum.android.diploma.data.db.entity.VacancyEntity
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

class FavoriteVacanciesConvertors(private val json: Gson) {
    fun mapToVacancyDetails(entity: VacancyEntity): VacancyDetails {
        return VacancyDetails(
            entity.vacancyId,
            entity.vacancyName,
            entity.address,
            entity.salary,
            entity.experience,
            getListItem(entity.keySkills),
            entity.vacancyDescription,
            entity.companyName,
            entity.companyLogoMedium,
            entity.companyLogoLittle,
            entity.email,
            entity.managerName,
            getListItem(entity.phones),
            entity.comment,
            entity.city,
            entity.employment,
            entity.schedule,
            entity.alternateUrl
        )
    }

    fun mapToEntity(details: VacancyDetails): VacancyEntity {
        return VacancyEntity(
            details.vacancyId,
            details.vacancyName,
            details.address,
            details.salary,
            details.experience,
            json.toJson(details.keySkills),
            details.vacancyDescription,
            details.companyName,
            details.companyLogoMedium,
            details.companyLogoLittle,
            details.email,
            details.managerName,
            json.toJson(details.phones),
            details.comment,
            details.city,
            details.employment,
            details.schedule,
            details.alternateUrl
        )
    }

    fun mapToVacansyItem(entity: VacancyEntity): VacancyItem {
        return VacancyItem(
            id = entity.vacancyId,
            city = entity.city,
            logo = entity.companyLogoLittle,
            nameVacancy = entity.vacancyName,
            nameCompany = entity.companyName,
            salary = entity.salary
        )
    }

    private fun getListItem(pramStr: String): List<String> {
        if (pramStr == "") return emptyList()
        return json.fromJson(pramStr, object : TypeToken<List<String>>() {}.type)
    }
}
