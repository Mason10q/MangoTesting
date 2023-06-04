package com.example.mangotesting.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangotesting.BaseViewModel
import com.example.mangotesting.network.AuthApi
import com.example.mangotesting.network.AuthReq
import com.example.mangotesting.network.ServerResponseMapper
import com.example.mangotesting.network.PhoneReq
import com.example.mangotesting.network.responses.CheckAuthCodeResponse
import com.example.mangotesting.network.responses.SendAuthCodeResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel : BaseViewModel() {

    @Inject
    lateinit var api: AuthApi

    private val _sendAuthResult = MutableLiveData<String>()
    val sendAuthResult: LiveData<String> = _sendAuthResult

    private val _checkAuthResult = MutableLiveData<CheckAuthCodeResponse>()
    val checkAuthResult: LiveData<CheckAuthCodeResponse> = _checkAuthResult


    fun sendAuthCode(phone: String) =
        composite.add(api.sendAuthCode(PhoneReq(phone))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ServerResponseMapper<SendAuthCodeResponse>().map(it) }
            .subscribe({
                _sendAuthResult.postValue(phone)
            }, {
                _error.postValue(it.message)
            })
        )

    fun checkAuthCode(phone: String, code: String) =
        composite.add(api.checkAuthCode(AuthReq(phone, code))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ServerResponseMapper<CheckAuthCodeResponse>().map(it) }
            .subscribe({
                _checkAuthResult.postValue(it)
            }, {
                _error.postValue(it.message)
            })
        )
}