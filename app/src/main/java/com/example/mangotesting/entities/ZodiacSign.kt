package com.example.mangotesting.entities

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object ZodiacSign {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    fun getZodiacSign(stringDate: String): String {
        return try {
            val date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            getZodiac(date.dayOfMonth, date.monthValue)
        } catch (e: Exception){
            ""
        }
    }


    fun getZodiac(day: Int, month: Int): String {
        return when (month) {
            1 -> if (day >= 20) "Водолей" else "Козерог"
            2 -> if (day >= 19) "Рыбы" else "Водолей"
            3 -> if (day >= 21) "Овен" else "Рыбы"
            4 -> if (day >= 20) "Телец" else "Овен"
            5 -> if (day >= 21) "Близнецы" else "Телец"
            6 -> if (day >= 21) "Рак" else "Близнецы"
            7 -> if (day >= 23) "Лев" else "Рак"
            8 -> if (day >= 23) "Дева" else "Лев"
            9 -> if (day >= 23) "Весы" else "Дева"
            10 -> if (day >= 23) "Скорпион" else "Весы"
            11 -> if (day >= 22) "Стрелец" else "Скорпион"
            12 -> if (day >= 22) "Козерог" else "Стрелец"
            else -> "Error"
        }
    }

}