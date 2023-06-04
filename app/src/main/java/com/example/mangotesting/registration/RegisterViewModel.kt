package com.example.mangotesting.registration

import android.support.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangotesting.BaseViewModel
import com.example.mangotesting.network.AuthApi
import com.example.mangotesting.network.ServerResponseMapper
import com.example.mangotesting.network.UserReq
import com.example.mangotesting.network.responses.RefreshTokenResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.IllegalStateException
import javax.inject.Inject

class RegisterViewModel: BaseViewModel() {

    @Inject lateinit var api: AuthApi

    private val _registerResult = MutableLiveData<RefreshTokenResponse?>()
    val registerResult: LiveData<RefreshTokenResponse?> = _registerResult

    fun registrate(phone: String, name: String, username: String) =
        api.register(UserReq(phone, name, username))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map{ ServerResponseMapper<RefreshTokenResponse>().map(it) }
            .subscribe({
                _registerResult.postValue(it)
            }, {
                if(it is IllegalStateException){
                    _error.postValue(it.message)
                }
            })
}