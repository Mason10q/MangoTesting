package com.example.mangotesting.auth

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangotesting.network.AuthApi
import com.example.mangotesting.network.AuthReq
import com.example.mangotesting.network.Mapper
import com.example.mangotesting.network.PhoneReq
import com.example.mangotesting.network.Resource
import com.example.mangotesting.network.dtos.ProfileDTO
import com.example.mangotesting.network.responses.CheckAuthCodeResponse
import com.example.mangotesting.network.responses.SendAuthCodeResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel: ViewModel() {

    @Inject lateinit var api: AuthApi

    private val _sendAuthResult = MutableLiveData<Boolean>()
    val sendAuthResult: LiveData<Boolean> = _sendAuthResult

    private val _checkAuthResult = MutableLiveData<CheckAuthCodeResponse>()
    val checkAuthResult: LiveData<CheckAuthCodeResponse> = _checkAuthResult

    fun sendAuthCode(number: String) =
        api.sendAuthCode(PhoneReq(number))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                when(val result = Mapper<SendAuthCodeResponse>().map(it)){
                    is Resource.Success -> _sendAuthResult.postValue(it.body()?.isSuccess)
                    is Resource.Error -> Log.d("Auth error", result.message.toString())
                }
            },{

            })

    fun checkAuthCode(phone: String, code: String) =
        api.checkAuthCode(AuthReq(phone, code))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                when(val result = Mapper<CheckAuthCodeResponse>().map(it)){
                    is Resource.Success -> _checkAuthResult.postValue(it.body())
                    is Resource.Error -> Log.d("Auth error", result.message.toString())
                }
            },{})

}