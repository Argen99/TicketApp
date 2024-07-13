package com.example.presentation.ui.adapter

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core.DisplayableItem
import com.example.domain.model.FlightControlButtonType
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

@RequiresApi(Build.VERSION_CODES.O)
class MainAdapter(
    onHintButtonItemClick: ((title: String) -> Unit)? = null,
    onRecDestItemClick: ((title: String) -> Unit)? = null,
    onFlightControlItemClick: ((actionType: FlightControlButtonType) -> Unit)? = null,
) : ListDelegationAdapter<List<DisplayableItem>>(
    offerAdapterDelegate(),
    hintButtonAdapterDelegate(onHintButtonItemClick),
    recommendedDestinationAdapterDelegate(onRecDestItemClick),
    flightControlAdapterDelegate(onFlightControlItemClick),
    ticketOfferAdapterDelegate(),
    ticketAdapterDelegate()
) {
    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<DisplayableItem>?) {
        super.setItems(items)
        notifyDataSetChanged()
    }
}