package com.example.mangotesting.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mangotesting.BaseViewModel
import com.example.mangotesting.entities.Profile
import com.example.mangotesting.mappers.ProfileMapper
import com.example.mangotesting.network.AuthApi
import com.example.mangotesting.mappers.ServerResponseMapper
import com.example.mangotesting.network.dtos.ProfileDTO
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel : BaseViewModel() {

    @Inject lateinit var api: AuthApi

    private val _profileData = MutableLiveData<Profile>()
    val profileData: LiveData<Profile> = _profileData
    private val serverResponseMapper = ServerResponseMapper<ProfileDTO>()

    fun getProfileData() =
        composite.add(api.getProfileData()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { serverResponseMapper.map(it) }
            .map { ProfileMapper.map(it) }
            .subscribe({
                _profileData.postValue(it)
            }, {
                _error.postValue(it.message)
            })
        )

}