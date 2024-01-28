package ru.practicum.android.diploma.presentation.vacancy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.practicum.android.diploma.databinding.KeySkillItemBinding
import ru.practicum.android.diploma.presentation.vacancy.viewholders.KeySkillViewHolder

class KeySkillsAdapter : ListAdapter<String, KeySkillViewHolder>(DetailsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeySkillViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val viewBinding = KeySkillItemBinding.inflate(layoutInspector, parent, false)
        return KeySkillViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: KeySkillViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}
