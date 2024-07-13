package com.example.data.remote.dto

import com.example.data.core.DataMapper
import com.example.domain.model.OffersRequest

data class OffersRequestDto(
    val offers: List<OfferDto>
): DataMapper<OffersRequest> {

    override fun toDomain() = OffersRequest(
        offers = offers.map { it.toDomain() }
    )
}