package com.example.domain.repository

import com.example.core.Either
import com.example.domain.model.OffersRequest
import com.example.domain.model.TicketModel
import com.example.domain.model.TicketOffersRequest
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getOffers(): Flow<Either<String, OffersRequest>>
    fun getTicketOffers(): Flow<Either<String, TicketOffersRequest>>
    fun getAllTickets(): Flow<Either<String, List<TicketModel>>>

    fun getLastDepartureLocation(): String?
    fun setLastDepartureLocation(location: String)
}