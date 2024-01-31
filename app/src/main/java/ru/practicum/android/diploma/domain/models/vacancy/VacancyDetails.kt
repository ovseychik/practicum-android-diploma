package ru.practicum.android.diploma.domain.models.vacancy

data class VacancyDetails(
    val vacancyId: String,
    val vacancyName: String,
    val address: String,
    val salary: String,
    val experience: String,
    val keySkills: List<String>,
    val vacancyDescription: String,
    val companyName: String,
    val companyLogoMedium: String,
    val companyLogoLittle: String,
    val email: String,
    val managerName: String,
    val phones: List<String>,
    val comment: String,
    val city: String,
    val employment: String, // тип занятости (полный день)
    val schedule: String, // график работы (удаленка)
    val alternateUrl: String
)
