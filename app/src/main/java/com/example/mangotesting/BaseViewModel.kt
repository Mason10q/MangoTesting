package com.example.mangotesting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    protected val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    protected val composite = CompositeDisposable()

    fun onStop(){
        composite.dispose()
    }

}