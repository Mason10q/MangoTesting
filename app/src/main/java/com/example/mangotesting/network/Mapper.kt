package com.example.mangotesting.network

import android.util.Log
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

class Mapper<T> {

    fun map(response: Response<T>): Resource<T>{

        try {
            val result = response.body()

            if (response.isSuccessful && result != null) {
                return Resource.Success(result)
            } else {
                return Resource.Error(getErrorMessage(response))
            }
        } catch (e: Exception){
            e.message?.let { Log.d("Request_fail", it) }
        }

        return Resource.Error("Error")
    }

    private fun getErrorMessage(response: Response<T>): String {

        val error = response.errorBody()?.string().toString()

        return when(response.code()){
            404 -> convertFromJson<NotFoundError>(error).message.toString()
            400 -> convertFromJson<BadRequest>(error).detail?.message.toString()
            422 -> convertFromJson<ValidationError>(error).errorLogs[0].message.toString()
            else -> "Another"
        }
    }

    private inline fun<reified E> convertFromJson(error: String): E {
        return Gson().fromJson(error, E::class.java)
    }

}