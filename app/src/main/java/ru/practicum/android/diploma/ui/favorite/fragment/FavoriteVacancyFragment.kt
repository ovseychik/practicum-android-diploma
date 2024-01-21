package ru.practicum.android.diploma.ui.favorite.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentFavoriteVacancyBinding
import ru.practicum.android.diploma.util.BindingFragment

class FavoriteVacancyFragment : BindingFragment<FragmentFavoriteVacancyBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteVacancyBinding {
        return FragmentFavoriteVacancyBinding.inflate(inflater, container, false)
    }
}
