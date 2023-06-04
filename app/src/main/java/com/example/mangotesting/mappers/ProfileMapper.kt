package com.example.mangotesting.mappers

import com.example.mangotesting.entities.Profile
import com.example.mangotesting.network.dtos.ProfileDTO

class ProfileMapper {

    fun map(response: ProfileDTO): Profile {
        return with(response) {
            Profile(name ?: "", username ?: "", phone ?: "", birthday ?: "", city ?: "")
        }
    }
}
