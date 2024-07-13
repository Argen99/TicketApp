package com.example.data.remote.api_service

import com.example.data.remote.dto.OffersRequestDto
import com.example.data.remote.dto.TicketOffersRequestDto
import com.example.data.remote.dto.TicketRequest
import retrofit2.http.GET

interface MainApiService {

    @GET(GET_OFFERS)
    suspend fun getOffers(): OffersRequestDto

    @GET(GET_TICKET_OFFERS)
    suspend fun getTicketOffers(): TicketOffersRequestDto

    @GET(GET_ALL_TICKETS)
    suspend fun getAllTickets(): TicketRequest

    companion object {
        const val GET_OFFERS = "u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download"
        const val GET_TICKET_OFFERS = "u/0/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download"
        const val GET_ALL_TICKETS = "uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA"
    }
}