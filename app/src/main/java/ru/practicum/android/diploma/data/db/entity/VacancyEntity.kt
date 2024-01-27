package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = VacancyEntity.TABLE_NAME)
data class VacancyEntity(
    @PrimaryKey
    val vacancyId: String?,
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
    val city: String?
) {
    companion object {
        const val TABLE_NAME = "vacancy_table"
    }
}
