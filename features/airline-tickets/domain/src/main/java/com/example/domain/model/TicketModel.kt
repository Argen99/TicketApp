package com.example.domain.model

import com.example.core.DisplayableItem

data class TicketModel(
    val id: Int,
    val badge: String?,
    val price: PriceModel,
    val departure: DestinationsModel,
    val arrival: DestinationsModel,
    val hasTransfer: Boolean,
): DisplayableItem