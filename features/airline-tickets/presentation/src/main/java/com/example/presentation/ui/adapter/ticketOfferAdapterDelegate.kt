package com.example.presentation.ui.adapter

import com.example.core.DisplayableItem
import com.example.core_ui.estensions.formatPrice
import com.example.domain.model.TicketOfferModel
import com.example.presentation.databinding.ItemTicketOfferBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun ticketOfferAdapterDelegate() = adapterDelegateViewBinding<TicketOfferModel, DisplayableItem, ItemTicketOfferBinding>(
    { layoutInflater, root -> ItemTicketOfferBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        val color = when(absoluteAdapterPosition) {
            0 -> getColor(com.example.core_ui.R.color.pink)
            1 -> getColor(com.example.core_ui.R.color.special_blue)
            else -> getColor(com.example.core_ui.R.color.white)
        }
        binding.ivImage.setBackgroundColor(color)
        binding.tvTitle.text = item.title
        binding.tvPrice.text = item.price.value.formatPrice()
        binding.tvTimeRange.text = item.timeRange.joinToString(" ")
    }
}