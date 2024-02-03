package ru.practicum.android.diploma.ui.filter.fragment.citypicker

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentCityPickerBinding
import ru.practicum.android.diploma.util.BindingFragment

class CityPickerFragment : BindingFragment<FragmentCityPickerBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCityPickerBinding {
        return FragmentCityPickerBinding.inflate(inflater, container, false)
    }
}
