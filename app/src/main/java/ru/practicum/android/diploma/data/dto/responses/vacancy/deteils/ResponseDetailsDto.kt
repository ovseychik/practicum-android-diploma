package ru.practicum.android.diploma.data.dto.responses.vacancy.deteils

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.responses.Response
import ru.practicum.android.diploma.data.dto.responses.vacancy.Area
import ru.practicum.android.diploma.data.dto.responses.vacancy.Employer
import ru.practicum.android.diploma.data.dto.responses.vacancy.Salary

data class ResponseDetailsDto(
    val id: String, // id вакансии
    val name: String, // название вакансии
    val salary: Salary, // внутри з/п
    @SerializedName("alternate_url")
    val alternateUrl: String, // ссылка на вакансию
    val area: Area, // внутри название регион
    val contacts: Contacts,  // контакты: имя, маил, коментарий, телефоны
    val description: String, // описание вакансии
    val employer: Employer, // внутри данные компании
    val experience: Experience, // внутри строка для заполнения поля опыт
    @SerializedName("key_skills")
    val keySkills: List<KeySkill>, // основные навыки
    @SerializedName(" professional_roles")
    val professionalRoles: List<ProfessionalRole>, // внури названия профессиональных ролей
    @SerializedName("published_at")
    val publishedAt: String, // дата и время публикации вакансии в формате "2013-07-08T16:17:21+0400"
) : Response()
