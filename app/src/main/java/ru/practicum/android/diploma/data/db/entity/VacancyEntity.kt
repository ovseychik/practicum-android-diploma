package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails

@Entity(tableName = VacancyEntity.TABLE_NAME)
data class VacancyEntity(
    @PrimaryKey
    val vacancyId: String,
    val vacancyName: String?,
    val address: String?,
    val salary: String?,
    val experience: String?,
    val keySkills: String?, // list<String>
    val vacancyDescription: String?,
    val companyName: String?,
    val companyLogoMedium: String?,
    val companyLogoLittle: String?,
    val email: String?,
    val managerName: String?,
    val phones: String?, // list<String>
    val comment: String?,
    val city: String?,
    val employment: String?,
    val schedule: String?,
) {
    companion object {
        const val TABLE_NAME = "vacancy_table"
    }
}
