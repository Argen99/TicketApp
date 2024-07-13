package com.example.data.remote.dto

import com.example.data.core.DataMapper
import com.example.domain.model.PriceModel

data class PriceDto(
    val value: Int
): DataMapper<PriceModel> {

    override fun toDomain() = PriceModel(
        value = value
    )
}