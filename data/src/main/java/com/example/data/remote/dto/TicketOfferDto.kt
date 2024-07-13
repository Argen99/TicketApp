package com.example.data.remote.dto

import com.example.data.core.DataMapper
import com.example.domain.model.TicketOfferModel
import com.google.gson.annotations.SerializedName

data class TicketOfferDto(
    val id: Int,
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val price: PriceDto
): DataMapper<TicketOfferModel> {

    override fun toDomain() = TicketOfferModel(
        id = id,
        title = title,
        timeRange = timeRange,
        price = price.toDomain(),
    )
}