package com.example.mangotesting.mappers

import com.example.mangotesting.entities.Profile
import com.example.mangotesting.network.AvatarReq
import com.example.mangotesting.network.UpdateUserReq

object UpdateProfileMapper {

    fun map(profile: Profile): UpdateUserReq {
        return with(profile) {
            UpdateUserReq(
                name, username, birthday, city,
                "", "", description, AvatarReq("", "")
            )
        }
    }

}