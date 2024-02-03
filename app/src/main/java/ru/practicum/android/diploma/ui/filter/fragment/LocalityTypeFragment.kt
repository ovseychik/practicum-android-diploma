package ru.practicum.android.diploma.ui.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentLocalityTypeBinding
import ru.practicum.android.diploma.util.BindingFragment

class LocalityTypeFragment : BindingFragment<FragmentLocalityTypeBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLocalityTypeBinding {
        return FragmentLocalityTypeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        with(binding) {
            etCountryLayout.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) {
                    binding.etCountry.setEndIconDrawable(R.drawable.ic_arrow_forward)
                } else {
                    binding.etCountry.setEndIconDrawable(R.drawable.ic_close)
                }
            }
            etCountry.setEndIconOnClickListener {
                binding.etCountryLayout.text?.clear()
            }
            etCountryLayout.setOnClickListener {
                // to country picker fragment
            }
            etRegionLayout.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) {
                    binding.etRegion.setEndIconDrawable(R.drawable.ic_arrow_forward)
                } else {
                    binding.etRegion.setEndIconDrawable(R.drawable.ic_close)
                }
            }
            etRegion.setEndIconOnClickListener {
                binding.etRegionLayout.text?.clear()
            }
            etRegionLayout.setOnClickListener {
                findNavController().navigate(R.id.action_localityTypeFragment_to_cityPickerFragment)
            }
        }

    }
}
