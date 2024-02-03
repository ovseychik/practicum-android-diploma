package ru.practicum.android.diploma.ui.filter.fragment.countrypicker

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentCountryPickerBinding
import ru.practicum.android.diploma.util.BindingFragment

class CountryPickerFragment : BindingFragment<FragmentCountryPickerBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCountryPickerBinding {
        return FragmentCountryPickerBinding.inflate(layoutInflater, container, false)
    }
}
