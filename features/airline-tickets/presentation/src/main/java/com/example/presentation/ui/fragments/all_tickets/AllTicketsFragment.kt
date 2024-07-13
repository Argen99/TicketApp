package com.example.presentation.ui.fragments.all_tickets

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAllTicketsBinding
import com.example.presentation.ui.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class AllTicketsFragment :
    BaseFragment<FragmentAllTicketsBinding, AllTicketsViewModel>(R.layout.fragment_all_tickets) {

    override val binding by viewBinding(FragmentAllTicketsBinding::bind)
    override val viewModel by viewModel<AllTicketsViewModel>()

    private val ticketsAdapter: MainAdapter by lazy {
        MainAdapter()
    }
    private val navArgs by navArgs<AllTicketsFragmentArgs>()

    override fun initialize() {
        setFlightDetails()
        binding.rvAllTickets.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ticketsAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setFlightDetails(): Unit = with(binding) {
        val departureLocation = navArgs.flightDetails.departureLocation
        val arrivalLocation = navArgs.flightDetails.arrivalLocation
        val departureDate = navArgs.flightDetails.departureDate
        val passengerCount = navArgs.flightDetails.passengerCount
        tvLocations.text = "$departureLocation-$arrivalLocation"
        tvFlightData.text = "$departureDate, $passengerCount"
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun launchObservers() {
        viewModel.ticketsState.spectateUiState(
            progressBar = binding.progressBar,
            success = {
                ticketsAdapter.items = it
            },
            error = {}
        )
    }
}