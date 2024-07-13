package com.example.presentation.ui.fragments.all_tickets

import com.example.core_ui.base.BaseViewModel
import com.example.domain.model.TicketModel
import com.example.domain.use_case.GetAllTicketsUseCase
import kotlinx.coroutines.flow.asStateFlow

class AllTicketsViewModel(
    private val getAllTicketsUseCase: GetAllTicketsUseCase
): BaseViewModel() {

    private val _ticketsState = mutableUiStateFlow<List<TicketModel>>()
    val ticketsState = _ticketsState.asStateFlow()

    init {
        getAllTickets()
    }

    private fun getAllTickets() {
        getAllTicketsUseCase().gatherRequest(_ticketsState)
    }
}