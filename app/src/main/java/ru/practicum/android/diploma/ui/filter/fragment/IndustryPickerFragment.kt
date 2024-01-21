package ru.practicum.android.diploma.ui.filter.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentIndustryPickerBinding
import ru.practicum.android.diploma.util.BindingFragment

class IndustryPickerFragment : BindingFragment<FragmentIndustryPickerBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIndustryPickerBinding {
        return FragmentIndustryPickerBinding.inflate(inflater, container, false)
    }
}
