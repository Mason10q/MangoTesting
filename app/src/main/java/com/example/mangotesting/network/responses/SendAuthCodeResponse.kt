package com.example.mangotesting.network.responses

import com.google.gson.annotations.SerializedName

data class SendAuthCodeResponse(
    @SerializedName("is_success")
    val isSuccess: Boolean = true
)