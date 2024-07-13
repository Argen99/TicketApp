package com.example.domain.model

import com.example.core.DisplayableItem

data class FlightControlButtonModel(
    val icon: Int?,
    val title: String,
    val type: FlightControlButtonType
): DisplayableItem