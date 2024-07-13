package com.example.core_ui.estensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Int.formatPrice(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        groupingSeparator = ' '
    }
    val formatter = DecimalFormat("#,###", symbols)
    return "${formatter.format(this)} ₽"
}

fun Int.getImageById(): Int = when (this) {
    1 -> com.example.core_ui.R.drawable.img_offer_1
    else -> com.example.core_ui.R.drawable.img_offer_2
}