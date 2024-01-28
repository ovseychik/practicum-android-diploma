package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.data.FavoritesRepositoryImpl
import ru.practicum.android.diploma.data.network.RepositoryDetailsImpl
import ru.practicum.android.diploma.data.network.RepositoryVacanciesImpl
import ru.practicum.android.diploma.domain.api.DetailsInteractor
import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.api.FavoritesRepository
import ru.practicum.android.diploma.domain.api.RepositoryDetails
import ru.practicum.android.diploma.domain.api.RepositoryVacancies
import ru.practicum.android.diploma.domain.api.VacanciesInteractor
import ru.practicum.android.diploma.domain.impl.details.DetailsInteractorImpl

import ru.practicum.android.diploma.domain.impl.search.VacanciesInteractorImpl

val domainModule = module {
    single<RepositoryDetails> {
        RepositoryDetailsImpl(client = get())
    }

    single<RepositoryVacancies> {
        RepositoryVacanciesImpl(client = get(), settingsPref = get(), json = get())
    }
    single<DetailsInteractor> {
        DetailsInteractorImpl(detailsRepository = get())
    }

    single<VacanciesInteractor> {
        VacanciesInteractorImpl(vacanciesRepository = get())
    }

    single<FavoritesRepository> {
        FavoritesRepositoryImpl(appDatabase = get(), vacancyConvertors = get())
    }
    single<FavoritesInteractor> {
        FavoritesInteractorImpl(favoritesRepository = get())
    }
}
