package com.example.presentation.ui.adapter

import com.example.core.DisplayableItem
import com.example.domain.model.HintButtonModel
import com.example.presentation.databinding.ItemHintButtonBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun hintButtonAdapterDelegate(itemClickListener: ((title: String) -> Unit)?) = adapterDelegateViewBinding<HintButtonModel, DisplayableItem, ItemHintButtonBinding>(
    { layoutInflater, root -> ItemHintButtonBinding.inflate(layoutInflater, root, false) }
) {
    binding.root.setOnClickListener {
        itemClickListener?.invoke(item.title)
    }

    bind {
        binding.ivImage.setImageResource(item.imageRes)
        binding.tvTitle.text = item.title
    }
}