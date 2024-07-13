package com.example.presentation.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_ui.base.BaseViewModel

class SearchViewModel: BaseViewModel() {

    private val _isDestinationFilled = MutableLiveData(false)
    val isDestinationFilled: LiveData<Boolean> = _isDestinationFilled

    fun setDestinationFilled(isFilled: Boolean) {
        _isDestinationFilled.value = isFilled
    }
}