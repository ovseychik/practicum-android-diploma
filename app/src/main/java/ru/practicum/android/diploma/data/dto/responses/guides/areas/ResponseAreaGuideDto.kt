package ru.practicum.android.diploma.data.dto.responses.guides.areas

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

data class ResponseAreaGuideDto(
    val areas: List<AreaGuide>,
    val id: String,
    val name: String,
    @SerializedName("parent_id")
    val parentId: String?
)

fun mapToListPlacesItem(areas: List<ResponseAreaGuideDto>): Map<Country, List<PlaceItem>> {
    val mapPlaceItem = mutableMapOf<Country, List<PlaceItem>>()
    areas.forEach {
        val listPlaceItem = mutableListOf<PlaceItem>()
        listPlaceItem.addAll(createdPlacesList(it.areas))
        mapPlaceItem[Country(it.name, it.id)] = listPlaceItem
    }
    return mapPlaceItem
}

private fun createdPlacesList(areas: List<AreaGuide>): List<PlaceItem> {
    val list = mutableListOf<PlaceItem>()
    areas.forEach {
        list.add(PlaceItem(areaName = it.name, areaId = it.id))
        if (!it.areas.isNullOrEmpty()) {
            list.addAll(createdPlacesList(it.areas))
        }
    }
    return list
}
