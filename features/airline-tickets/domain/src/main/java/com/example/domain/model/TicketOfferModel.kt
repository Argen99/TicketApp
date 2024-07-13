package com.example.domain.model

import com.example.core.DisplayableItem

data class TicketOfferModel(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceModel
): DisplayableItem