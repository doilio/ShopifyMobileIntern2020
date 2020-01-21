package com.doilio.shopifymemory.fragments.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(private val pairs: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(pairs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}