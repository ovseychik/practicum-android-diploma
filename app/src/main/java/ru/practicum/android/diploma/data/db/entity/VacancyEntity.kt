package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = VacancyEntity.TABLE_NAME)
data class VacancyEntity(
    @PrimaryKey
    val id: String,
    val url: String?,
    val name: String?,
    val salaryFrom: String?,
    val salaryTo: String?,
    val currency: String?,
    val gross: Boolean?,
    val employerId: String?,
    val area: String?,
    val experienceId: String?,
    val employment: String?,
    val schedule: String?,
    val contactPerson: String?,
    val email: String?,
    val phone: String?,
    val description: String?,
    val keySkills: String?,
    val professionalRoles: String?,
    val publishedAt: String?,
    val comment: String?,
) {
    companion object {
        const val TABLE_NAME = "vacancy_table"
    }
}
