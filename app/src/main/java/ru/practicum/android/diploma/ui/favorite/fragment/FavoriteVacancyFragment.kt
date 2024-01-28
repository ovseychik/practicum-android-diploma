package ru.practicum.android.diploma.ui.favorite.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentFavoriteVacancyBinding
import ru.practicum.android.diploma.presentation.favorite.FavoriteViewModel
import ru.practicum.android.diploma.util.BindingFragment

class FavoriteVacancyFragment : BindingFragment<FragmentFavoriteVacancyBinding>() {
    private val viewModel by viewModel<FavoriteViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteVacancyBinding {
        return FragmentFavoriteVacancyBinding.inflate(inflater, container, false)
    }
}
