package com.foodapp.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.atomic.AtomicBoolean

open class BaseViewModel : ViewModel(){

    private val initiated = AtomicBoolean()

    private val viewModelJob = SupervisorJob()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun initOnce(onInit: () -> Unit) {
        if (initiated.compareAndSet(false, true)) {
            onInit.invoke()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}