package com.example.data.remote.dto

import com.example.data.core.DataMapper
import com.example.domain.model.TicketModel
import com.google.gson.annotations.SerializedName

data class TicketDto(
    val id: Int,
    val badge: String,
    val price: PriceDto,
    val departure: DestinationsDto,
    val arrival: DestinationsDto,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
): DataMapper<TicketModel> {
    override fun toDomain() = TicketModel(
        id = id,
        badge = badge,
        price = price.toDomain(),
        departure = departure.toDomain(),
        arrival = arrival.toDomain(),
        hasTransfer = hasTransfer
    )
}