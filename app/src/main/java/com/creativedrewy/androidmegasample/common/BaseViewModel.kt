package com.creativedrewy.androidmegasample.common

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {
    private var loadingDisposable: Disposable? = null

    fun performLoadOperation(block: () -> Disposable) {
        loadingDisposable = block()
    }

    override fun onCleared() {
        loadingDisposable?.dispose()
    }
}