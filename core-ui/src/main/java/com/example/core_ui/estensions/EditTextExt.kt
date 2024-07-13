package com.example.core_ui.estensions

import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView

fun EditText.setupClearButtonWithAction(clearButton: ImageView) {
    clearButton.visibility = View.INVISIBLE

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            clearButton.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    clearButton.setOnClickListener {
        this.text.clear()
    }
}

fun EditText.setCyrillicInputFilter() {
    val cyrillicInputFilter = object : InputFilter {
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            for (i in start until end) {
                if (!source[i].toString().matches(Regex("[\\p{IsCyrillic}\\s]"))) {
                    return ""
                }
            }
            return null
        }
    }
    this.filters = arrayOf(cyrillicInputFilter)
}