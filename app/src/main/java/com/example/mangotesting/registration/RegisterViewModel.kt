package com.example.mangotesting.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangotesting.network.AuthApi
import com.example.mangotesting.network.Mapper
import com.example.mangotesting.network.Resource
import com.example.mangotesting.network.UserReq
import com.example.mangotesting.network.responses.RefreshTokenResponse
import com.example.mangotesting.network.responses.SendAuthCodeResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel: ViewModel() {

    @Inject lateinit var api: AuthApi

    private val _registerResult = MutableLiveData<RefreshTokenResponse>()
    val registerResult: LiveData<RefreshTokenResponse> = _registerResult

    fun registrate(phone: String, name: String, username: String) =
        api.register(UserReq(phone, name, username))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                when(val result = Mapper<RefreshTokenResponse>().map(it)){
                    is Resource.Success -> _registerResult.postValue(it.body())
                    is Resource.Error -> Log.d("Auth error", result.message.toString())
                }
            }, {

            })


}