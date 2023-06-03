package com.example.mangotesting.network

import com.google.gson.annotations.SerializedName

data class ValidationError(
    @SerializedName("detail")
    val errorLogs: List<Detail>
)

data class Detail(
    @SerializedName("loc")
    val loc: List<String?>?,
    @SerializedName("msg")
    val message: String? = "",
    @SerializedName("type")
    val type: String? = ""
)

data class NotFoundError(
    @SerializedName("message")
    val message: String = ""
)