package com.witzeal.pagination.presentation.splash


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.witzeal.pagination.presentation.core.base.BaseViewModel

class SplashViewModel : BaseViewModel() {
    private val _onStartEvent = MutableLiveData<String>()
    val onStartEvent: LiveData<String>
        get() = _onStartEvent

    val welcomeText: String = "A Coding Assignment for Witzeal Technologies Pvt. Ltd. " +
            "Please press the start button. Thank you for providing me this opportunity"

    fun startListFragment() {
        _onStartEvent.value = "NavigateToGridScreen"
    }
}
