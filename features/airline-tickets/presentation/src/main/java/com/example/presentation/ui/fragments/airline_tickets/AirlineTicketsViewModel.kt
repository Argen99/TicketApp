package com.example.presentation.ui.fragments.airline_tickets

import com.example.core_ui.base.BaseViewModel
import com.example.domain.model.OffersRequest
import com.example.domain.use_case.GetLastDepartureLocation
import com.example.domain.use_case.GetOffersUseCase
import com.example.domain.use_case.SetLastDepartureLocation
import kotlinx.coroutines.flow.asStateFlow

class AirlineTicketsViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getLastDepartureLocationUseCase: GetLastDepartureLocation,
    private val setLastDepartureLocationUseCase: SetLastDepartureLocation,
): BaseViewModel() {

    private val _offersState = mutableUiStateFlow<OffersRequest>()
    val offersState = _offersState.asStateFlow()

    init {
        getOffers()
    }

    private fun getOffers() {
        getOffersUseCase().gatherRequest(_offersState)
    }

    fun getLastDepartureLocation(): String? {
        return getLastDepartureLocationUseCase()
    }

    fun setLastDepartureLocation(location: String) {
        setLastDepartureLocationUseCase(location)
    }
}