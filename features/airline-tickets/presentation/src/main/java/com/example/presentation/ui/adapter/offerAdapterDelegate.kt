package com.example.presentation.ui.adapter

import android.annotation.SuppressLint
import com.example.core.DisplayableItem
import com.example.core_ui.estensions.formatPrice
import com.example.core_ui.estensions.getImageById
import com.example.domain.model.OfferModel
import com.example.presentation.databinding.ItemOfferBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@SuppressLint("SetTextI18n")
fun offerAdapterDelegate() = adapterDelegateViewBinding<OfferModel, DisplayableItem, ItemOfferBinding>(
    { layoutInflater, root -> ItemOfferBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.ivImage.setImageResource(item.id.getImageById())
        binding.tvTitle.text = item.title
        binding.tvCityName.text = item.town
        binding.tvPrice.text = "от ${item.price.value.formatPrice()}"
    }
}