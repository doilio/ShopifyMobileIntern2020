package com.doilio.shopifymemory.fragments.winner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class WinnerViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WinnerViewModel::class.java)) {
            return WinnerViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}