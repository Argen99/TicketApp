package com.example.presentation.ui.fragments.airline_tickets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.estensions.safeNavigation
import com.example.core_ui.estensions.setCyrillicInputFilter
import com.example.core_ui.estensions.showShortToast
import com.example.core_ui.utils.MarginItemDecoration
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAirlineTicketsBinding
import com.example.presentation.ui.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class AirlineTicketsFragment : BaseFragment<FragmentAirlineTicketsBinding, AirlineTicketsViewModel>(
    R.layout.fragment_airline_tickets
) {
    override val binding by viewBinding(FragmentAirlineTicketsBinding::bind)
    override val viewModel by viewModel<AirlineTicketsViewModel>()
    private val offerAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun initialize() {
        binding.etDepartureLocation.setText(viewModel.getLastDepartureLocation())
        binding.etDepartureLocation.setCyrillicInputFilter()
        binding.rvOffers.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = offerAdapter
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(com.example.core_ui.R.dimen.item_offer_spacing)))
        }
    }

    override fun setupListeners() {
        binding.tvArrivalLocation.setOnClickListener {
            if (binding.etDepartureLocation.text.isNullOrEmpty()) {
                showShortToast("Заполните поле \"Откуда\"")
            } else {
                val departureLocation = binding.etDepartureLocation.text.toString()
                viewModel.setLastDepartureLocation(departureLocation)
                findNavController().safeNavigation(
                    AirlineTicketsFragmentDirections.actionAirlineTicketsFragmentToSearchBottomSheet(
                        departureLocation
                    )
                )
            }
        }
    }

    override fun launchObservers() {
        viewModel.offersState.spectateUiState(
            progressBar = binding.progressBar,
            success = {
                offerAdapter.items = it.offers
            },
            error = {
                showShortToast(it)
            }
        )
    }
}