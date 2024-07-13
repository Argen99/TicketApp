package com.example.presentation.ui.adapter

import androidx.core.view.isVisible
import com.example.core.DisplayableItem
import com.example.domain.model.FlightControlButtonModel
import com.example.domain.model.FlightControlButtonType
import com.example.presentation.databinding.ItemFlightControlBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun flightControlAdapterDelegate(
    onItemClick: ((actionType: FlightControlButtonType) -> Unit)?
) = adapterDelegateViewBinding<FlightControlButtonModel, DisplayableItem, ItemFlightControlBinding>(
    { layoutInflater, root -> ItemFlightControlBinding.inflate(layoutInflater, root, false) }
) {
    binding.root.setOnClickListener {
        onItemClick?.invoke(item.type)
    }

    bind {
        item.icon?.let {
            binding.ivIcon.isVisible = true
            binding.ivIcon.setImageResource(it)
        }
        binding.tvTitle.text = item.title
    }
}