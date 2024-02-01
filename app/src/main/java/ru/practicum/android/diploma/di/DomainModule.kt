package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.data.ExternalNavigatorImpl
import ru.practicum.android.diploma.data.FavoritesRepositoryImpl
import ru.practicum.android.diploma.data.network.guide.IndustriesRepositoryImpl
import ru.practicum.android.diploma.data.network.guide.PlacesRepositoryImpl
import ru.practicum.android.diploma.data.network.vacancies.RepositoryDetailsImpl
import ru.practicum.android.diploma.data.network.vacancies.RepositoryVacanciesImpl
import ru.practicum.android.diploma.data.settings.SettingsRepositoryImpl
import ru.practicum.android.diploma.domain.api.DetailsInteractor
import ru.practicum.android.diploma.domain.api.ExternalNavigator
import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.api.FavoritesRepository
import ru.practicum.android.diploma.domain.api.RepositoryDetails
import ru.practicum.android.diploma.domain.api.RepositoryVacancies
import ru.practicum.android.diploma.domain.api.VacanciesInteractor
import ru.practicum.android.diploma.domain.api.guides.IndustriesRepository
import ru.practicum.android.diploma.domain.api.guides.PlacesRepository
import ru.practicum.android.diploma.domain.api.settings.SettingsRepository
import ru.practicum.android.diploma.domain.impl.details.DetailsInteractorImpl
import ru.practicum.android.diploma.domain.impl.favorite.FavoritesInteractorImpl
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

    single<ExternalNavigator> {
        ExternalNavigatorImpl(androidContext())
    }

    single<IndustriesRepository> {
        IndustriesRepositoryImpl(client = get())
    }

    single<PlacesRepository> {
        PlacesRepositoryImpl(client = get())
    }
    single<SettingsRepository> {
        SettingsRepositoryImpl(storage = get())
    }
}
