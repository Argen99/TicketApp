package com.example.presentation.ui.fragments.search.filled_destination

import com.example.core_ui.base.BaseViewModel
import com.example.domain.model.TicketOffersRequest
import com.example.domain.use_case.GetTicketOffersUseCase
import kotlinx.coroutines.flow.asStateFlow

class FilledDestinationsViewModel(
    private val getTicketGetOffersUseCase: GetTicketOffersUseCase
): BaseViewModel() {

    private val _ticketOffersState = mutableUiStateFlow<TicketOffersRequest>()
    val ticketOffersState = _ticketOffersState.asStateFlow()

    init {
        getTicketOffers()
    }

    private fun getTicketOffers(){
        getTicketGetOffersUseCase().gatherRequest(_ticketOffersState)
    }
}