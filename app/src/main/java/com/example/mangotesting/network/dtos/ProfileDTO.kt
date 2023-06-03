package com.example.mangotesting.network.dtos

import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("birthday")
    val birthday: String? = "",
    @SerializedName("city")
    val city: String? = "",
    @SerializedName("vk")
    val vk: String? = "",
    @SerializedName("instagram")
    val instagram: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("avatar")
    val avatar: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("last")
    val last: String? = "",
    @SerializedName("online")
    val isOnline: Boolean? = false,
    @SerializedName("created")
    val created: String? = "",
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("completed_task")
    val completedTaskCount: Int? = 0,
    @SerializedName("avatars")
    val avatars: AvatarsDTO?
)