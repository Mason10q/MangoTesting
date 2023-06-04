package com.example.mangotesting.entities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.TreeMap


object ZodiacSign{

    @SuppressLint("SimpleDateFormat")
    fun getZodiacSign(stringDate: String): String{
        val date = SimpleDateFormat("yyyy-MM-dd").parse(stringDate)
        return zodiacSign(date!!.day, date.month).toString()

    }


    private val SIGNS = TreeMap<Int, String>().apply {
        this[1_01] = "Capricorn"
        this[1_20] = "Aquarius"
        this[2_18] = "Pisces"
        this[3_21] = "Aries"
        this[4_20] = "Taurus"
        this[5_21] = "Gemini"
        this[6_21] = "Cancer"
        this[7_23] = "Leo"
        this[8_23] = "Pisces"
        this[9_23] = "Libra"
        this[10_23] = "Scorpio"
        this[11_22] = "Sagittarius"
        this[12_22] = "Capricorn"
    }


    private fun zodiacSign(day: Int, month: Int): String? {
        val key = month * 100 + day // 10 feb = 210 = 2_10
        return SIGNS.floorEntry(key)?.value
    }

}