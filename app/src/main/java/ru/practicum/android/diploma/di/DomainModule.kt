package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.data.network.RepositoryDetailsImpl
import ru.practicum.android.diploma.data.network.RepositoryVacanciesImpl
import ru.practicum.android.diploma.domain.api.DetailsInteractor
import ru.practicum.android.diploma.domain.api.RepositoryDetails
import ru.practicum.android.diploma.domain.api.RepositoryVacancies
import ru.practicum.android.diploma.domain.impl.details.DetailsInteractorImpl

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
}
