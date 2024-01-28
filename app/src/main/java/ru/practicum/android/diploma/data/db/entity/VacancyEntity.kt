package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT

@Entity(tableName = VacancyEntity.TABLE_NAME)
data class VacancyEntity(
    @PrimaryKey
    val vacancyId: String,
    val vacancyName: String = EMPTY_PARAM_SRT,
    val address: String = EMPTY_PARAM_SRT,
    val salary: String = EMPTY_PARAM_SRT,
    val experience: String = EMPTY_PARAM_SRT,
    val keySkills: String = EMPTY_PARAM_SRT, // list<String>
    val vacancyDescription: String = EMPTY_PARAM_SRT,
    val companyName: String = EMPTY_PARAM_SRT,
    val companyLogoMedium: String = EMPTY_PARAM_SRT,
    val companyLogoLittle: String = EMPTY_PARAM_SRT,
    val email: String = EMPTY_PARAM_SRT,
    val managerName: String = EMPTY_PARAM_SRT,
    val phones: String = EMPTY_PARAM_SRT, // list<String>
    val comment: String = EMPTY_PARAM_SRT,
    val city: String = EMPTY_PARAM_SRT,
    val employment: String = EMPTY_PARAM_SRT,
    val schedule: String = EMPTY_PARAM_SRT,
) {
    companion object {
        const val TABLE_NAME = "vacancy_table"
    }
}
