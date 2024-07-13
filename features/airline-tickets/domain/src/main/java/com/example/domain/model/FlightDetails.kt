package com.example.domain.model

import java.io.Serializable

data class FlightDetails(
    val departureLocation: String,
    val arrivalLocation: String,
    val departureDate: String,
    val passengerCount: String
): Serializable
