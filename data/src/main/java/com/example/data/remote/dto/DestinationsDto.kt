package com.example.data.remote.dto

import com.example.data.core.DataMapper
import com.example.domain.model.DestinationsModel

data class DestinationsDto(
    val date: String,
    val airport: String,
): DataMapper<DestinationsModel> {
    override fun toDomain() = DestinationsModel(
        date = date,
        airport = airport,
    )
}