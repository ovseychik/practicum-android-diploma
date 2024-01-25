package ru.practicum.android.diploma.domain.models.vacansy

data class VacancyDetails(
    val vacancyId: String, //id вакасии
    val vacancyName: String,
    val salary: String?,
    val experience: String,
    val keySkills: List<String>,
    val vacancyDescription: String,
    val companyName: String,
    val companyLogoMedium: String,
    val companyLogoLittle: String,
    val email: String,
    val managerName: String,
    val phones: List<String>,
    val comment: String?
)

