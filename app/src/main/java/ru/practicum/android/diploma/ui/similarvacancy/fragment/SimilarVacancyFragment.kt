package ru.practicum.android.diploma.ui.similarvacancy.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentSimilarVacancyBinding
import ru.practicum.android.diploma.util.BindingFragment

class SimilarVacancyFragment : BindingFragment<FragmentSimilarVacancyBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSimilarVacancyBinding {
        return FragmentSimilarVacancyBinding.inflate(inflater, container, false)
    }
}
