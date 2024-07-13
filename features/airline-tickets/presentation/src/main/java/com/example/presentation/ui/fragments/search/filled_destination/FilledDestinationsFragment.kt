package com.example.presentation.ui.fragments.search.filled_destination

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.estensions.safeNavigation
import com.example.core_ui.estensions.showShortToast
import com.example.core_ui.estensions.toFormattedDateString
import com.example.core_ui.utils.DateTimeFormatPatterns
import com.example.domain.model.FlightControlButtonModel
import com.example.domain.model.FlightControlButtonType
import com.example.domain.model.FlightDetails
import com.example.presentation.R
import com.example.presentation.databinding.FragmentFilledDestinationsBinding
import com.example.presentation.ui.adapter.MainAdapter
import com.example.presentation.ui.dialogs.DatePickerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class FilledDestinationsFragment :
    BaseFragment<FragmentFilledDestinationsBinding, FilledDestinationsViewModel>(R.layout.fragment_filled_destinations) {

    override val binding by viewBinding(FragmentFilledDestinationsBinding::bind)
    override val viewModel by viewModel<FilledDestinationsViewModel>()
    private val navArgs by navArgs<FilledDestinationsFragmentArgs>()

    private val flightControlAdapter: MainAdapter by lazy {
        MainAdapter(onFlightControlItemClick = ::onFlightControlItemClick)
    }
    private val ticketOfferAdapter: MainAdapter by lazy {
        MainAdapter()
    }
    private var departureDate: String? = null

    private val flightControlButtons: MutableList<FlightControlButtonModel> by lazy {
        val calendar = Calendar.getInstance()
        departureDate = calendar.time.toFormattedDateString()
        mutableListOf(
            FlightControlButtonModel(
                com.example.core_ui.R.drawable.ic_plus,
                "Обратно",
                FlightControlButtonType.RETURN_DATE
            ),
            FlightControlButtonModel(null, departureDate!!, FlightControlButtonType.DEPARTURE_DATE),
            FlightControlButtonModel(
                com.example.core_ui.R.drawable.ic_person_2,
                "1,эконом",
                FlightControlButtonType.PASSENGER_COUNT_AND_CLASS
            ),
            FlightControlButtonModel(
                com.example.core_ui.R.drawable.ic_filter,
                "фильтры",
                FlightControlButtonType.FILTERS
            ),
        )
    }

    override fun initialize() {
        binding.tvDepartureLocation.text = navArgs.departureLocation
        binding.tvArrivalLocation.text = navArgs.arrivalLocation
        setupFlightControlRv()
        setupTicketOfferRv()
    }

    override fun setupListeners(): Unit = with(binding) {
        btnSwap.setOnClickListener {
            val departureLocation = tvDepartureLocation.text.toString()
            val arrivalLocation = tvArrivalLocation.text.toString()
            tvDepartureLocation.text = arrivalLocation
            tvArrivalLocation.text = departureLocation
        }
        btnOpenAllTickets.setOnClickListener {
            val flightDetails = FlightDetails(
                departureLocation = binding.tvDepartureLocation.text.toString(),
                arrivalLocation = binding.tvArrivalLocation.text.toString(),
                departureDate = departureDate ?: "",
                passengerCount = "1 пассажир"
            )
            findNavController().safeNavigation(
                FilledDestinationsFragmentDirections.actionFilledDestinationSearchFragmentToAllTicketsFragment(
                    flightDetails
                )
            )
        }
    }

    private fun setupTicketOfferRv() {
        binding.rvTicketOffers.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ticketOfferAdapter
        }
    }

    private fun setupFlightControlRv() {
        binding.rvFlightControl.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = flightControlAdapter
        }
        flightControlAdapter.items = flightControlButtons
    }

    override fun launchObservers() {
        viewModel.ticketOffersState.spectateUiState(
            progressBar = binding.progressBar,
            success = {
                ticketOfferAdapter.items = it.ticketsOffers
            },
            error = {
                showShortToast(it)
            }
        )
    }

    private fun onFlightControlItemClick(actionType: FlightControlButtonType): Unit =
        when (actionType) {
            FlightControlButtonType.RETURN_DATE -> {
                showDatePicker(FlightControlButtonType.RETURN_DATE)
            }

            FlightControlButtonType.DEPARTURE_DATE -> {
                showDatePicker(FlightControlButtonType.DEPARTURE_DATE)
            }

            FlightControlButtonType.PASSENGER_COUNT_AND_CLASS -> {}
            FlightControlButtonType.FILTERS -> {}
        }

    private fun showDatePicker(type: FlightControlButtonType) {
        val datePicker = DatePickerFragment { date ->
            val index =
                flightControlButtons.indexOfFirst { it.type == type }
            flightControlButtons[index] =
                flightControlButtons[index].copy(title = date.toFormattedDateString(), icon = null)
            flightControlAdapter.notifyItemChanged(index)
            if (type == FlightControlButtonType.DEPARTURE_DATE)
                departureDate =
                    date.toFormattedDateString(pattern = DateTimeFormatPatterns.DAY_MONTH_PATTERN)
        }
        datePicker.show(childFragmentManager, "timePicker")
    }
}