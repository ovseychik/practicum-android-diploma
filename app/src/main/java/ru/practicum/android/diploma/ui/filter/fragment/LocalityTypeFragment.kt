package ru.practicum.android.diploma.ui.filter.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentLocalityTypeBinding
import ru.practicum.android.diploma.util.BindingFragment

class LocalityTypeFragment : BindingFragment<FragmentLocalityTypeBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLocalityTypeBinding {
        return FragmentLocalityTypeBinding.inflate(inflater, container, false)
    }
}
