package ru.practicum.android.diploma.ui.team.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentTeamBinding
import ru.practicum.android.diploma.util.BindingFragment

class TeamFragment : BindingFragment<FragmentTeamBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTeamBinding {
        return FragmentTeamBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_bottom)
        val blinkAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.blink)
        lifecycleScope.launch {
            binding.teamMember1.startAnimation(inAnim)
            binding.teamMember2.startAnimation(inAnim)
            binding.teamMember3.startAnimation(inAnim)
            binding.teamMember4.startAnimation(inAnim)
            delay(DELAY_ANIM)
            binding.teamMember1.startAnimation(blinkAnim)
            binding.teamMember2.startAnimation(blinkAnim)
            binding.teamMember3.startAnimation(blinkAnim)
            binding.teamMember4.startAnimation(blinkAnim)
        }
    }

    companion object {
        private const val DELAY_ANIM = 1500L
    }
}
