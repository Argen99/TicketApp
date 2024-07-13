package com.example.domain.model

import com.example.core.DisplayableItem

data class OfferModel(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceModel,
): DisplayableItem