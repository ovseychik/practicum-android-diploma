package ru.practicum.android.diploma.presentation.vacancy.viewholders

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.databinding.PhoneItemBinding

class PhoneViewHolder(private val parentBinding: PhoneItemBinding): RecyclerView.ViewHolder(parentBinding.root) {
    fun bind(phone: String){
        parentBinding.tvPhone.setText(phone)
    }
}
