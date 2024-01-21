package ru.practicum.android.diploma.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.data.db.entity.VacancyEntity

interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacancyEntity)

    @Query("SELECT * FROM vacancy_table")
    suspend fun getVacancies(): List<VacancyEntity>

    @Query("SELECT * FROM vacancy_table WHERE id = :id")
    suspend fun getVacancy(id: String): VacancyEntity?

    @Delete
    suspend fun deleteVacancy(vacancy: VacancyEntity)

}
