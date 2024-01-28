package ru.practicum.android.diploma.presentation.vacancy.viewholders

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.KeySkillItemBinding

class KeySkillViewHolder(private val parentBinding: KeySkillItemBinding) : RecyclerView.ViewHolder(parentBinding.root) {
    fun bind(keySkill: String) {
        parentBinding.tvSkillName.text = keySkill
    }
}
