package com.example.presentation.ui.adapter

import com.example.core.DisplayableItem
import com.example.domain.model.RecommendedDestinationsModel
import com.example.presentation.databinding.ItemRecommendedDestinationBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun recommendedDestinationAdapterDelegate(
    onItemClick: ((title: String) -> Unit)? = null
) =
    adapterDelegateViewBinding<RecommendedDestinationsModel, DisplayableItem, ItemRecommendedDestinationBinding>(
        { layoutInflater, root ->
            ItemRecommendedDestinationBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        binding.root.setOnClickListener {
            onItemClick?.invoke(item.title)
        }

        bind {
            binding.ivImage.setImageResource(item.imageRes)
            binding.tvTitle.text = item.title
        }
    }