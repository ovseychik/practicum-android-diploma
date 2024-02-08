package ru.practicum.android.diploma.ui.vacancydetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.databinding.FragmentVacancyDetailsBinding
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.presentation.vacancy.adapters.KeySkillsAdapter
import ru.practicum.android.diploma.presentation.vacancy.adapters.PhonesAdtapter
import ru.practicum.android.diploma.presentation.vacancy.models.ScreenStateDetails
import ru.practicum.android.diploma.presentation.vacancy.viewmodel.DetailsViewModel
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.VACANCY_ID
import ru.practicum.android.diploma.util.debounce
import ru.practicum.android.diploma.util.dpToPx

private const val RADIUS_ICON_DP = 12.0f

class VacancyDetailsFragment : BindingFragment<FragmentVacancyDetailsBinding>() {
    private val detailsViewModel by viewModel<DetailsViewModel>()
    private var clickedPhoneDebounce: ((String) -> Unit)? = null
    private val phoneAdapter = PhonesAdtapter { phone ->
        clickedPhoneDebounce?.let { it(phone) }
    }
    private val keySkillsAdapter = KeySkillsAdapter()
    private var vacancyId: String = EMPTY_PARAM_SRT

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVacancyDetailsBinding {
        return FragmentVacancyDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        setOnPhoneClickListener()
        detailsViewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
        detailsViewModel.currentVacancyInFavorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.addToFavoriteButton.isChecked = true
            } else {
                binding.addToFavoriteButton.isChecked = false
            }
        }
        with(binding) {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun bind() {
        vacancyId = requireArguments().getString(VACANCY_ID) ?: EMPTY_PARAM_SRT
        detailsViewModel.getVacancyDetails(vacancyId)
        with(binding) {
            phoneList.adapter = phoneAdapter
            phoneList.layoutManager = LinearLayoutManager(requireContext())
            keySkillsList.adapter = keySkillsAdapter
            keySkillsList.layoutManager = LinearLayoutManager(requireContext())
        }
        detailsViewModel.checkedVacancyForFavorite(vacancyId)
    }

    private fun setOnPhoneClickListener() {
        clickedPhoneDebounce = debounce<String>(CLICK_DELAY, lifecycleScope, false) {
            detailsViewModel.openDial(it)
        }
    }

    private fun toastExceptionShowing() {
        detailsViewModel.isToastShowing.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, requireContext().getString(R.string.app_not_found), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun render(screenStateDetails: ScreenStateDetails) {
        when (screenStateDetails) {
            is ScreenStateDetails.IsLoading -> showLoading()
            is ScreenStateDetails.Content -> showContent(screenStateDetails.details)
            is ScreenStateDetails.Error -> showError()
            is ScreenStateDetails.NoInternet -> processingNoInternet()
            is ScreenStateDetails.NoVacansyFromDb -> showNoInternet()
        }
    }

    private fun showContent(details: VacancyDetails) {
        val radiusIconVacancyPx = dpToPx(RADIUS_ICON_DP, requireContext())
        with(binding) {
            setVisibilityContent()
            vacancyName.text = details.vacancyName
            salary.text = details.salary
            companyName.text = details.companyName
            companyLocation.text = if (details.address == EMPTY_PARAM_SRT) details.city else details.address
            experience.text = details.experience
            vacancyDescription.text = details.vacancyDescription
            comment.text = details.comment
            contactsPerson.text = details.managerName
            Glide.with(requireContext())
                .load(details.companyLogoLittle)
                .transform(FitCenter(), RoundedCorners(radiusIconVacancyPx))
                .placeholder(R.drawable.ic_vacancy_logo_placeholder)
                .into(companyLogo)
            if (details.keySkills.isNullOrEmpty()) {
                keySkillsBlock.isVisible = false
            } else {
                keySkillsBlock.isVisible = true
                keySkillsAdapter.submitList(details.keySkills)
            }
            shareButton.setOnClickListener {
                detailsViewModel.sharingLink(details.alternateUrl)
            }
        }
        setEmployment(details)
        setContacts(details)
        binding.addToFavoriteButton.setOnClickListener {
            detailsViewModel.changedVacancyFavorite(details)
        }
    }

    private fun showError() {
        with(binding) {
            vacancyDetailsList.isVisible = false
            noInternet.root.isVisible = false
            serverError.root.isVisible = true
            progressBar.root.isVisible = false
        }
    }

    private fun showLoading() {
        with(binding) {
            vacancyDetailsList.isVisible = false
            noInternet.root.isVisible = false
            serverError.root.isVisible = false
            progressBar.root.isVisible = true
        }
    }

    private fun processingNoInternet() {
        detailsViewModel.getVacancyFromDb(vacancyId)
    }

    private fun showNoInternet() {
        with(binding) {
            vacancyDetailsList.isVisible = false
            noInternet.root.isVisible = true
            serverError.root.isVisible = false
            progressBar.root.isVisible = false
        }
    }

    private fun setVisibilityContent() {
        with(binding) {
            vacancyDetailsList.isVisible = true
            noInternet.root.isVisible = false
            serverError.root.isVisible = false
            progressBar.root.isVisible = false
        }
    }

    private fun setContacts(details: VacancyDetails) {
        with(binding) {
            if (details.phones.isNotEmpty()) {
                contactsHeader.isVisible = true
                phoneHeader.isVisible = true
                phoneList.isVisible = true
                phoneAdapter.submitList(details.phones)
            }
            if (details.email != EMPTY_PARAM_SRT) {
                contactsHeader.isVisible = true
                emailHeader.isVisible = true
                email.isVisible = true
                email.text = details.email
                email.setOnClickListener {
                    detailsViewModel.sendEmail(details.email)
                    toastExceptionShowing()
                }
            }
            if (details.managerName != EMPTY_PARAM_SRT) {
                contactsHeader.isVisible = true
                contactsPersonHeader.isVisible = true
                contactsPerson.isVisible = true
                contactsPerson.text = details.managerName
            }
            if (details.comment != EMPTY_PARAM_SRT) {
                contactsHeader.isVisible = true
                commentHeader.isVisible = true
                comment.isVisible = true
                comment.text = details.comment
            }
        }
    }

    private fun setEmployment(details: VacancyDetails) {
        with(binding) {
            if (details.employment != EMPTY_PARAM_SRT) {
                employment.text = "${details.employment}, ${details.schedule}"
            } else {
                employment.text = details.schedule
            }

        }
    }

    companion object {
        private const val CLICK_DELAY = 300L
    }

}
