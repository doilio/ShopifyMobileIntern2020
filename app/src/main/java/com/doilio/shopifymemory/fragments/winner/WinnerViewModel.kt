package com.doilio.shopifymemory.fragments.winner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WinnerViewModel : ViewModel() {

    private var _navigateToGameFragment = MutableLiveData<Boolean>()
    val navigateToGameFragment: LiveData<Boolean>
        get() = _navigateToGameFragment


    fun onNavigateToGameFragment() {
        _navigateToGameFragment.value = true
    }

    fun navigateCompleted() {
        _navigateToGameFragment.value = false
    }
}