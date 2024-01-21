package ru.practicum.android.diploma.ui.vacancydetails.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentVacancyDetailsBinding
import ru.practicum.android.diploma.util.BindingFragment

class VacancyDetailsFragment : BindingFragment<FragmentVacancyDetailsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVacancyDetailsBinding {
        return FragmentVacancyDetailsBinding.inflate(inflater, container, false)
    }
}
