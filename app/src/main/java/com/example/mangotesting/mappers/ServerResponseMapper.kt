package com.example.mangotesting.mappers

import com.example.mangotesting.network.NotFoundError
import com.example.mangotesting.network.ValidationError
import com.google.gson.Gson
import retrofit2.Response

class ServerResponseMapper<T> {

    fun map(response: Response<T>): T {
        val result = response.body()

        if (response.isSuccessful && result != null) {
            return result
        } else {
            throw IllegalStateException(getErrorMessage(response))
        }
    }

    private fun getErrorMessage(response: Response<T>): String {

        val error = response.errorBody()?.string().toString()

        return when(response.code()){
            404 -> convertFromJson<NotFoundError>(error).detail?.message.toString()
            400 -> convertFromJson<NotFoundError>(error).detail?.message.toString()
            422 -> convertFromJson<ValidationError>(error).errorLogs[0].message.toString()
            else -> "Another"
        }
    }

    private inline fun<reified E> convertFromJson(error: String): E {
        return Gson().fromJson(error, E::class.java)
    }

}