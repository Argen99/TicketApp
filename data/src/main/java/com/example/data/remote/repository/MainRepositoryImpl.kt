package com.example.data.remote.repository

import com.example.core.Either
import com.example.data.core.makeNetworkRequest
import com.example.data.local.PreferencesManager
import com.example.data.remote.api_service.MainApiService
import com.example.domain.model.OffersRequest
import com.example.domain.model.TicketModel
import com.example.domain.model.TicketOffersRequest
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: MainApiService,
    private val prefs: PreferencesManager,
): MainRepository {

    override fun getOffers(): Flow<Either<String, OffersRequest>> = makeNetworkRequest {
        apiService.getOffers().toDomain()
    }

    override fun getTicketOffers(): Flow<Either<String, TicketOffersRequest>> = makeNetworkRequest {
        apiService.getTicketOffers().toDomain()
    }

    override fun getAllTickets(): Flow<Either<String, List<TicketModel>>> = makeNetworkRequest {
        apiService.getAllTickets().tickets.map { it.toDomain() }
    }

    override fun getLastDepartureLocation(): String? {
        return prefs.lastDepartureLocation
    }

    override fun setLastDepartureLocation(location: String) {
        prefs.lastDepartureLocation = location
    }
}