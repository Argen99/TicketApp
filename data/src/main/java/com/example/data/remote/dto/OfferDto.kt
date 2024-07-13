package com.example.data.remote.dto

import com.example.data.core.DataMapper
import com.example.domain.model.OfferModel

data class OfferDto(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceDto,
): DataMapper<OfferModel> {

    override fun toDomain() = OfferModel(
        id = id,
        title = title,
        town = town,
        price = price.toDomain(),
    )
}