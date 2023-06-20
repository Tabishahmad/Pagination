package com.witzeal.pagination.presentation.splash


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookapi.presentation.core.base.BaseViewModel

class SplashViewModel : BaseViewModel() {
    private val _onStartEvent = MutableLiveData<String>()
    val onStartEvent: LiveData<String>
        get() = _onStartEvent

    fun startListFragment() {
        _onStartEvent.value = "NavigateToGridScreen"
    }
}
