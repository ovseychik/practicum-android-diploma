package ru.practicum.android.diploma.data.dto.responses.guides.inustries

import ru.practicum.android.diploma.domain.models.guides.IndustryItem

data class ResponseIndustriesGuideItem(
    val id: String,
    val industries: List<Industry>, // список подиндустрий
    val name: String // название индустрии
)

fun mapToListIndustriesItem(listDto: List<ResponseIndustriesGuideItem>): List<IndustryItem> {
    val listItem = mutableListOf<IndustryItem>()
    listDto.forEach {
        listItem.addAll(createdListIndusriesItem(it.industries))
    }
    return listItem
}

private fun createdListIndusriesItem(list: List<Industry>): List<IndustryItem> {
    val addedList = mutableListOf<IndustryItem>()
    list.forEach { addedList.add(IndustryItem(it.name)) }
    return addedList
}
