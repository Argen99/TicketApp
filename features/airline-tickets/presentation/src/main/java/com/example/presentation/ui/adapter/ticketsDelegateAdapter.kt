package com.example.presentation.ui.adapter

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.core.DisplayableItem
import com.example.core_ui.estensions.calculateFlightDurationTo
import com.example.core_ui.estensions.formatDate
import com.example.core_ui.estensions.formatPrice
import com.example.domain.model.TicketModel
import com.example.presentation.databinding.ItemTicketBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@SuppressLint("SetTextI18n")
@RequiresApi(Build.VERSION_CODES.O)
fun ticketAdapterDelegate() = adapterDelegateViewBinding<TicketModel, DisplayableItem, ItemTicketBinding>(
    { layoutInflater, root -> ItemTicketBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        item.badge?.let { badge ->
            binding.tvBadge.isVisible = true
            binding.tvBadge.text = badge
        } ?: {
            binding.tvBadge.isVisible = false
        }
        binding.tvBadge.text = item.badge
        binding.tvPrice.text = item.price.value.formatPrice()
        binding.tvDeparture.text = item.departure.date.formatDate()
        binding.tvArrival.text = item.arrival.date.formatDate()
        binding.tvArrival.text = item.arrival.date.formatDate()
        binding.tvDepartureAirportCode.text = item.departure.airport
        binding.tvArrivalAirportCode.text = item.arrival.airport
        binding.tvArrivalAirportCode.text = item.arrival.airport
        val hours = item.departure.date calculateFlightDurationTo item.arrival.date
        val isHasTransfer = if(item.hasTransfer) "" else " / Без пересадок"
        binding.tvFlightTime.text = "${hours}ч в пути$isHasTransfer"
    }
}