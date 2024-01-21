package ru.practicum.android.diploma.ui.filter.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.util.BindingFragment

class FilterFragment : BindingFragment<FragmentFilterBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilterBinding {
        return FragmentFilterBinding.inflate(inflater, container, false)
    }

}
