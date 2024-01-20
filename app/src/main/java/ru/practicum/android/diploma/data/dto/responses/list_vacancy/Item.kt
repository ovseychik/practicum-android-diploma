package com.example.myapplication.models.list_vacancy

data class Item(
    val id: String, // идентификатор вакансии
    val area: Area, // внутри название город
    val employer: Employer, // внутри информация об организации и лого
    val name: String, // название вакансии
    val published_at: String, // дата публикации в формате "2013-10-11T13:27:16+0400"
    val salary: Salary, // внутри з/п
    val show_logo_in_search: Boolean, // показывать ли логотип при поиске
)