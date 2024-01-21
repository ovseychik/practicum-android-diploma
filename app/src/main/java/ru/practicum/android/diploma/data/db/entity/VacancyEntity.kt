package ru.practicum.android.diploma.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = VacancyEntity.TABLE_NAME)
data class VacancyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "currency")
    val currency: String?,
    @ColumnInfo(name = "salaryFrom")
    val salaryFrom: Int?,
    @ColumnInfo(name = "salaryTo")
    val salaryTo: Int?,
    @ColumnInfo(name = "gross")
    val gross: Boolean?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "area")
    val area: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "phone")
    val phone: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "employerId")
    val employerId: String?,
    @ColumnInfo(name = "experienceId")
    val experienceId: String?,
    @ColumnInfo(name = "keySkills")
    val keySkills: String?,
    @ColumnInfo(name = "professionalRoles")
    val professionalRoles: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,
) {
    companion object {
        const val TABLE_NAME = "vacancy_table"
    }
}
