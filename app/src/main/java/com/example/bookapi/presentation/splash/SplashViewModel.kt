package com.example.bookapi.presentation.splash


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookapi.presentation.core.base.BaseViewModel

class SplashViewModel : BaseViewModel() {
    private val _onStartEvent = MutableLiveData<String>()
    val onStartEvent: LiveData<String>
        get() = _onStartEvent

    fun onPressStartButton() {
        _onStartEvent.value = "NavigateToGridScreen"
    }
}
