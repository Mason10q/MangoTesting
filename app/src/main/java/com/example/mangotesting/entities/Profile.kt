package com.example.mangotesting.entities

data class Profile(
    val name: String = "",
    val username: String = "",
    val phone: String = "",
    val birthday: String = "",
    val city: String = ""
){
    val zodiacSign: String = ZodiacSign.getZodiacSign(birthday)
}