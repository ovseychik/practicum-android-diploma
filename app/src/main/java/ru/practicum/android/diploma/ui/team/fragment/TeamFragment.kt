package ru.practicum.android.diploma.ui.team.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
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
            setAnimationTextViews(
                binding.teamMember1,
                binding.teamMember2,
                binding.teamMember3,
                binding.teamMember4,
                anim = inAnim
            )
            delay(DELAY_ANIM)
            setAnimationTextViews(
                binding.teamMember1,
                binding.teamMember2,
                binding.teamMember3,
                binding.teamMember4,
                anim = blinkAnim
            )
        }
    }

    private fun setAnimationTextViews(vararg view: TextView, anim: Animation) {
        view.forEach {
            it.startAnimation(anim)
        }
    }

    companion object {
        private const val DELAY_ANIM = 1500L
    }
}
