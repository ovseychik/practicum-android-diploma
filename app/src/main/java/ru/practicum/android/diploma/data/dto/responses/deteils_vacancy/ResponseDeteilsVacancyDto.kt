package com.example.myapplication.models.current_vacancy

import ru.practicum.android.diploma.data.dto.responses.Response

data class ResponseDeteilsVacancyDto(
    val id: String, // id вакансии
    val name: String, // название вакансии
    val salary: Salary, // внутри з/п
    val alternate_url: String, // ссылка на вакансию
    val area: Area, // внутри название регион
    val branded_description: String, // альтернативное описание вакансии html
    val can_upgrade_billing_type: Boolean,
    val contacts: Contacts,  // контакты: имя, маил, коментарий, телефоны
    val created_at: String, // дата публикации м/б для сортировки?
    val description: String, // описание вакансии
    val employer: Employer, // внутри данные компании
    val experience: Experience, // внутри строка для заполнения поля опыт
    val key_skills: List<KeySkill>, // основные навыки
    val professional_roles: List<ProfessionalRole>, // внури названия профессиональных ролей
    val published_at: String, // дата и время публикации вакансии в формате "2013-07-08T16:17:21+0400"
) : Response()
