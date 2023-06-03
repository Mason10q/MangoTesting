package com.example.mangotesting.network

import android.util.Log
import com.google.gson.Gson
import retrofit2.Response

class Mapper<T> {

    fun map(response: Response<T>): Resource<T>{
        val result = response.body()

        try {
            return if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(getErrorMessage(response))
            }
        } catch (e: Exception){
            e.message?.let { Log.d("Request_fail", it) }
        }

        return Resource.Error("Error")
    }

    private fun getErrorMessage(response: Response<T>): String?{
        if(response.code() == 422) {
            return convertFromJson<ValidationError>(response).errorLogs[0].message
        }
        else if(response.code() == 404){
            return convertFromJson<NotFoundError>(response).message
        }

        return "Another Error"
    }

    private inline fun<reified E> convertFromJson(response: Response<T>): E {
        return Gson().fromJson(response.errorBody().toString(), E::class.java)
    }

}