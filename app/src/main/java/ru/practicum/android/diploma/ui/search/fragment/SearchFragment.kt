package ru.practicum.android.diploma.ui.search.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem
import ru.practicum.android.diploma.presentation.models.ScreenStateVacancies
import ru.practicum.android.diploma.presentation.vacancy.VacancyAdapter
import ru.practicum.android.diploma.presentation.viewmodel.SearhViewModel
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.VACANCY
import ru.practicum.android.diploma.util.debounce

class SearchFragment : BindingFragment<FragmentSearchBinding>() {

    private val viewModel by viewModel<SearhViewModel>()
    private val vacancyAdapter = VacancyAdapter {vacancyItem ->
        vacancyClickDebounce?.let { it1 -> it1(vacancyItem) }
    }
    private var vacancyClickDebounce: ((VacancyItem) -> Unit)? = null
    private var currentQuery = ""

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        setOnVacancyClickListener()
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun bind() {
        with(binding) {
            etSearch.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrBlank()) {
                    btnClear.setImageResource(R.drawable.ic_search)
                } else {
                    btnClear.setImageResource(R.drawable.ic_close)
                    if (text.toString() != currentQuery) {
                        currentQuery = text.toString()
                        viewModel.debounceSearch(currentQuery)
                    }
                }
            }

            btnClear.setOnClickListener {
                etSearch.text.clear()
            }

            rvSearchResult.adapter = vacancyAdapter
        }
    }

    private fun setOnVacancyClickListener() {
        vacancyClickDebounce = debounce(
            CLICK_DEBOUNCE_DELAY_MILLIS,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { vacancyItem ->
            val vacancyBundle = bundleOf(VACANCY to Gson().toJson(vacancyItem))
            findNavController().navigate(R.id.action_searchFragment_to_vacancyDetailsFragment, vacancyBundle)
        }
    }

    private fun render(state: ScreenStateVacancies) {
        when (state) {
            is ScreenStateVacancies.Content -> {
                showContent(state.listVacancies, state.foundItems)
            }

            is ScreenStateVacancies.Empty -> {
                showEmptyState(state.message)
            }

            is ScreenStateVacancies.Error -> {
                showServerErrorState(state.message)
            }

            is ScreenStateVacancies.IsLoading -> {
                showLoadingState()
            }

            is ScreenStateVacancies.NoInternet -> {
                showNoInternetState(state.message)
            }
        }
    }

    private fun showContent(vacanciesList: List<VacancyItem>, itemsFound: Int) {
        with(binding) {
            rvSearchResult.isVisible = true
            tvResultCountChips.isVisible = true
            tvErrorPlaceholder.isVisible = false
            ivPicPlaceholder.isVisible = false
            pbCircle.isVisible = false
            tvResultCountChips.text = itemsFound.toString()
            vacancyAdapter.clearData()
            vacancyAdapter.addVacancies(vacanciesList)
        }
    }

    private fun showEmptyState(message: Int) {
        with(binding) {
            rvSearchResult.isVisible = false
            tvResultCountChips.isVisible = false
            tvErrorPlaceholder.isVisible = true
            ivPicPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_nothing_found_pic)
            tvErrorPlaceholder.text = getString(message)
        }
    }

    private fun showServerErrorState(message: Int) {
        with(binding) {
            rvSearchResult.isVisible = false
            tvResultCountChips.isVisible = false
            tvErrorPlaceholder.isVisible = true
            ivPicPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_nothing_found_pic)
            tvErrorPlaceholder.text = getString(message)
        }
    }

    private fun showLoadingState() {
        with(binding) {
            rvSearchResult.isVisible = false
            tvResultCountChips.isVisible = false
            tvErrorPlaceholder.isVisible = false
            ivPicPlaceholder.isVisible = false
            pbCircle.isVisible = true
        }
    }

    private fun showNoInternetState(message: Int) {
        with(binding) {
            rvSearchResult.isVisible = false
            tvResultCountChips.isVisible = false
            tvErrorPlaceholder.isVisible = true
            ivPicPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_no_internet_pic)
            tvErrorPlaceholder.text = getString(message)
        }
    }

    companion object {
        const val CLICK_DEBOUNCE_DELAY_MILLIS = 1000L
    }
}
