package com.example.mangotesting.entities

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable

data class Profile(
    val name: String = "",
    val username: String = "",
    val phone: String = "",
    val birthday: String = "",
    val city: String = "",
    val description: String = ""
): Serializable {
    @RequiresApi(Build.VERSION_CODES.O)
    val zodiacSign: String = ZodiacSign.getZodiacSign(birthday)
}