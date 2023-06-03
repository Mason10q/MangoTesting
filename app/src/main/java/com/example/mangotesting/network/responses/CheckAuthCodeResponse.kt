package com.example.mangotesting.network.responses

import com.google.gson.annotations.SerializedName

data class CheckAuthCodeResponse(
    @SerializedName("refresh_token")
    val refreshToken: String = "",
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("is_user_exists")
    val isUserExists: Boolean = false
)
