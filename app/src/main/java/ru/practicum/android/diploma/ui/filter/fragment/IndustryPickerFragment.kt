package ru.practicum.android.diploma.ui.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentIndustryPickerBinding
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.presentation.settings.adapters.IndustryAdapter
import ru.practicum.android.diploma.presentation.settings.models.IndustriesScreenState
import ru.practicum.android.diploma.presentation.settings.viewmodels.IndustriesViewModel
import ru.practicum.android.diploma.ui.filter.fragment.industry.IndustryUiModel
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.debounce

class IndustryPickerFragment : BindingFragment<FragmentIndustryPickerBinding>() {
    private val viewModel by viewModel<IndustriesViewModel>()
    private val industryAdapter = IndustryAdapter { industryItem ->
        clickedDebounce?.let { it(industryItem) }
        viewModel.onIndustryItemClicked(industryItem)
    }
    private var clickedDebounce: ((IndustryItem) -> Unit)? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIndustryPickerBinding {
        return FragmentIndustryPickerBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
        bind()
        viewModel.getIndustries()
        applySelectedIndustry()
    }

    private fun render(screenState: IndustriesScreenState) {
        when (screenState) {
            is IndustriesScreenState.Content -> showContent(
                screenState.industriesList,
                screenState.selectedIndustryName
            )

            is IndustriesScreenState.Empty -> showEmptyState()
            is IndustriesScreenState.NoInternet -> showNoInternetState()
            is IndustriesScreenState.Error -> showErrorState()
            is IndustriesScreenState.Loading -> showLoadingState()
        }
    }

    private fun bind() {
        with(binding) {
            etSearch.doAfterTextChanged { text ->
                viewModel.filteredIndustries(text.toString())
                if (text.isNullOrEmpty()) {
                    btnClear.setImageResource(R.drawable.ic_search)
                } else {
                    btnClear.setImageResource(R.drawable.ic_close)
                }
            }
            btnClear.setOnClickListener {
                etSearch.text.clear()
            }
            rvSearchResult.layoutManager = LinearLayoutManager(requireContext())
            rvSearchResult.adapter = industryAdapter
            btnSelect.setOnClickListener {
                viewModel.saveSelectedIndustry()
                findNavController().navigateUp()
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun applySelectedIndustry() {
        clickedDebounce = debounce(
            CLICK_DEBOUNCE_DELAY_MILLIS,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { industryItem ->
            viewModel.onIndustryItemClicked(industryItem)
        }
    }

    private fun showContent(industryItems: List<IndustryItem>, selectedIndustryName: String) {
        with(binding) {
            rvSearchResult.isVisible = true
            ivPicPlaceholder.isVisible = false
            tvErrorPlaceholder.isVisible = false
            pbCircle.isVisible = false
            industryAdapter.submitList(industryItems.map {
                IndustryUiModel(
                    it,
                    it.industryName == selectedIndustryName
                )
            })
            btnSelect.isVisible = selectedIndustryName.isNotEmpty()
        }
    }

    private fun showEmptyState() {
        with(binding) {
            ivPicPlaceholder.isVisible = false
            tvErrorPlaceholder.isVisible = false
            rvSearchResult.isVisible = false
            btnSelect.isVisible = false
            pbCircle.isVisible = false
        }
    }

    private fun showNoInternetState() {
        with(binding) {
            ivPicPlaceholder.apply {
                setImageResource(R.drawable.ic_no_internet_pic)
                isVisible = true
            }
            tvErrorPlaceholder.apply {
                text = getString(R.string.no_internet_error_text)
                isVisible = true
            }
            rvSearchResult.isVisible = false
            btnSelect.isVisible = false
            pbCircle.isVisible = false
        }
    }

    private fun showErrorState() {
        with(binding) {
            ivPicPlaceholder.apply {
                setImageResource(R.drawable.ic_server_error_regions)
                isVisible = true
            }
            tvErrorPlaceholder.apply {
                text = getString(R.string.empty_industry_list)
                isVisible = true
            }
            rvSearchResult.isVisible = false
            btnSelect.isVisible = false
            pbCircle.isVisible = false
        }
    }

    private fun showLoadingState() {
        with(binding) {
            pbCircle.isVisible = true
            rvSearchResult.isVisible = false
            ivPicPlaceholder.isVisible = false
            tvErrorPlaceholder.isVisible = false
            rvSearchResult.isVisible = false
            btnSelect.isVisible = false
        }
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY_MILLIS = 200L
    }
}
