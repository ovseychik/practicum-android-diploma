package ru.practicum.android.diploma.ui.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFavoriteVacancyBinding
import ru.practicum.android.diploma.presentation.favorite.model.FavoriteScreenState
import ru.practicum.android.diploma.presentation.favorite.viewmodel.FavoriteViewModel
import ru.practicum.android.diploma.presentation.vacancy.VacancyAdapter
import ru.practicum.android.diploma.util.BindingFragment

class FavoriteVacancyFragment : BindingFragment<FragmentFavoriteVacancyBinding>() {
    private val viewModel by viewModel<FavoriteViewModel>()

    private val vacancyAdapter = VacancyAdapter {}

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteVacancyBinding {
        return FragmentFavoriteVacancyBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSearchResult.adapter = vacancyAdapter
        viewModel.fillData()
        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        // Реализовать переход на детали по клику issue #77

    }

    override fun onResume() {
        super.onResume()
        viewModel.fillData()
    }

    private fun render(state: FavoriteScreenState) {
        when (state) {
            is FavoriteScreenState.Content -> showContent()
            FavoriteScreenState.Empty -> showEmpty()
            FavoriteScreenState.Error -> shorError()
        }
    }

    private fun showContent() {
        binding.rvSearchResult.isVisible = true
        binding.llErrorPlaceholder.isVisible = false
    }

    private fun showEmpty() {
        binding.rvSearchResult.isVisible = false
        binding.llErrorPlaceholder.isVisible = true

        binding.ivPicPlaceholder.setImageResource(R.drawable.ic_empty_list_favorite_pic)
        binding.tvErrorPlaceholder.text = R.string.favorite_empty_list_error_text.toString()
    }

    private fun shorError() {
        binding.rvSearchResult.isVisible = false
        binding.llErrorPlaceholder.isVisible = true

        binding.ivPicPlaceholder.setImageResource(R.drawable.ic_error_favorite_list_pic)
        binding.tvErrorPlaceholder.text = R.string.favorite_db_error_text.toString()
    }

}
