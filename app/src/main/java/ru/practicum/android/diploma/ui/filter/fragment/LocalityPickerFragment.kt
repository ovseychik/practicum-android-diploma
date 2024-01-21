package ru.practicum.android.diploma.ui.filter.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentLocalityPickerBinding
import ru.practicum.android.diploma.util.BindingFragment

class LocalityPickerFragment : BindingFragment<FragmentLocalityPickerBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLocalityPickerBinding {
        return FragmentLocalityPickerBinding.inflate(inflater, container, false)
    }
}
