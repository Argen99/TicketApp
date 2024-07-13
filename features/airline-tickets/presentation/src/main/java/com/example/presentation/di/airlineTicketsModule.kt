package com.example.presentation.di

import com.example.domain.di.ticketsDomainModule
import com.example.presentation.ui.fragments.airline_tickets.AirlineTicketsViewModel
import com.example.presentation.ui.fragments.search.filled_destination.FilledDestinationsViewModel
import com.example.presentation.ui.fragments.search.SearchViewModel
import com.example.presentation.ui.fragments.all_tickets.AllTicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val airlineTicketsModule = module {
    includes(ticketsDomainModule)
    viewModelOf(::AirlineTicketsViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::FilledDestinationsViewModel)
    viewModelOf(::AllTicketsViewModel)
}