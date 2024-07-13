package com.example.data.remote.dto

import com.example.data.core.DataMapper
import com.example.domain.model.TicketOffersRequest
import com.google.gson.annotations.SerializedName

data class TicketOffersRequestDto(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketOfferDto>
): DataMapper<TicketOffersRequest> {

    override fun toDomain() = TicketOffersRequest(
        ticketsOffers = ticketsOffers.map { it.toDomain() }
    )
}