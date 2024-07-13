package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import com.example.domain.use_case.GetOffersUseCase
import com.example.domain.use_case.GetTicketOffersUseCase
import com.example.domain.use_case.GetAllTicketsUseCase
import com.example.domain.use_case.SetLastDepartureLocation
import com.example.domain.use_case.GetLastDepartureLocation
import org.koin.dsl.module

val ticketsDomainModule = module {
    factoryOf(::GetOffersUseCase)
    factoryOf(::GetTicketOffersUseCase)
    factoryOf(::GetAllTicketsUseCase)
    factoryOf(::GetTicketOffersUseCase)
    factoryOf(::SetLastDepartureLocation)
    factoryOf(::GetLastDepartureLocation)
}