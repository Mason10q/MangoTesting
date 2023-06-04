package com.example.mangotesting.network

import com.example.mangotesting.network.dtos.AvatarsDTO
import com.example.mangotesting.network.dtos.ProfileDTO
import com.example.mangotesting.network.responses.CheckAuthCodeResponse
import com.example.mangotesting.network.responses.RefreshTokenResponse
import com.example.mangotesting.network.responses.SendAuthCodeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT



interface AuthApi {

    @POST("send-auth-code/")
    fun sendAuthCode(@Body phone: PhoneReq): Single<Response<SendAuthCodeResponse>>

    @POST("check-auth-code/")
    fun checkAuthCode(@Body authReq: AuthReq): Single<Response<CheckAuthCodeResponse>>

    @POST("register/")
    fun register(@Body user: UserReq): Single<Response<RefreshTokenResponse>>


    @POST("refresh-token/")
    fun refreshToken(@Body refreshTokenReq: RefreshTokenReq): Single<Response<RefreshTokenResponse>>

    @GET("me/")
    fun getProfileData(): Single<Response<ProfileDTO>>

    @PUT("me/")
    fun updateUser(): Single<Response<AvatarsDTO>>

    @GET("check_jwt/")
    fun checkJwt(): Single<Response<String>>

}