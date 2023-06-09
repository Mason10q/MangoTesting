package com.example.mangotesting.network

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class PhoneReq(
    @SerializedName("phone")
    val phone: String
)

data class AuthReq(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("code")
    val code: String
)

data class UserReq(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
)

data class RefreshTokenReq(
    @SerializedName("refresh_token")
    val code: String
)

data class AvatarReq(
    @SerializedName("filename")
    val fileName: String,
    @SerializedName("base_64")
    val base64: String
)

data class UpdateUserReq(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("vk")
    val vk: String,
    @SerializedName("instagram")
    val instagram: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("avatar")
    val avatar: AvatarReq
)