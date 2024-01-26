package ru.practicum.android.diploma.ui.search.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.util.BindingFragment

class SearchFragment : BindingFragment<FragmentSearchBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        with(binding) {
            etSearch.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrBlank()) {
                    btnClear.setImageResource(R.drawable.ic_search)
                    // возможно, стоит переделать, когда будет state-класс
                    ivPicPlaceholder.visibility = View.VISIBLE
                } else {
                    btnClear.setImageResource(R.drawable.ic_close)
                    ivPicPlaceholder.visibility = View.GONE
                }
            }

            btnClear.setOnClickListener {
                etSearch.text.clear()
            }
        }
    }

}
