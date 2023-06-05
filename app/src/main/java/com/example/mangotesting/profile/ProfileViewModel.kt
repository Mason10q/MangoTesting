package com.example.mangotesting.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mangotesting.BaseViewModel
import com.example.mangotesting.entities.Profile
import com.example.mangotesting.mappers.ProfileMapper
import com.example.mangotesting.network.AuthApi
import com.example.mangotesting.mappers.ServerResponseMapper
import com.example.mangotesting.mappers.UpdateProfileMapper
import com.example.mangotesting.network.dtos.ProfileDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
            .observeOn(AndroidSchedulers.mainThread())
            .map { serverResponseMapper.map(it) }
            .map { ProfileMapper.map(it) }
            .subscribe({
                _profileData.postValue(it)
            }, {
                _error.postValue(it.message)
            })
        )


    fun updateProfile(profile: Profile) =
        composite.add(api.updateUser(UpdateProfileMapper.map(profile))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                _error.postValue(it.message)
            })
        )

}