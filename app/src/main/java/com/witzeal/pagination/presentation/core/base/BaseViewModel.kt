package com.witzeal.pagination.presentation.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    protected fun performCoroutineTask(block: suspend  () -> Unit) {
        viewModelScope.launch() {
            block()
        }
    }
}
