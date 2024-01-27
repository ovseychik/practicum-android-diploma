package ru.practicum.android.diploma.data.db.convertors

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.practicum.android.diploma.data.db.entity.VacancyEntity
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

class FavoriteVacanciesConvertors(private val json: Gson) {
    fun mapToVacancyDetails(entity: VacancyEntity): VacancyDetails {
        return VacancyDetails(
            entity.vacancyId,
            entity.vacancyName ?: EMPTY_PARAM_SRT,
            entity.address ?: EMPTY_PARAM_SRT,
            entity.salary ?: EMPTY_PARAM_SRT,
            entity.experience ?: EMPTY_PARAM_SRT,
            getListIatem(entity.keySkills ?: EMPTY_PARAM_SRT),
            entity.vacancyDescription ?: EMPTY_PARAM_SRT,
            entity.companyName ?: EMPTY_PARAM_SRT,
            entity.companyLogoMedium ?: EMPTY_PARAM_SRT,
            entity.companyLogoLittle ?: EMPTY_PARAM_SRT,
            entity.email ?: EMPTY_PARAM_SRT,
            entity.managerName ?: EMPTY_PARAM_SRT,
            getListIatem(entity.phones ?: EMPTY_PARAM_SRT),
            entity.comment ?: EMPTY_PARAM_SRT,
            entity.city ?: EMPTY_PARAM_SRT,
            entity.employment ?: EMPTY_PARAM_SRT,
            entity.schedule ?: EMPTY_PARAM_SRT,
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
        )
    }

    fun mapToVacansyItem(entity: VacancyEntity): VacancyItem {
        return VacancyItem(
            entity.vacancyId,
            entity.vacancyName ?: EMPTY_PARAM_SRT,
            entity.companyLogoLittle ?: EMPTY_PARAM_SRT,
            entity.vacancyName ?: EMPTY_PARAM_SRT,
            entity.companyName ?: EMPTY_PARAM_SRT,
            entity.salary ?: EMPTY_PARAM_SRT
        )
    }

    private fun getListIatem(pramStr: String): List<String> {
        if (pramStr == "") return emptyList()
        return json.fromJson(pramStr, object : TypeToken<List<String>>() {}.type)
    }
}
