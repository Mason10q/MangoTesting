package com.example.mangotesting.network.dtos

import com.google.gson.annotations.SerializedName

data class AvatarsDTO(
    @SerializedName("avatar")
    val avatar: String? = "",
    @SerializedName("big_avatar")
    val bigAvatar: String? = "",
    @SerializedName("miniAvatar")
    val miniAvatar: String? = ""
)