package com.example.mangotesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mangotesting.network.AuthReq
import com.example.mangotesting.network.Mapper
import com.example.mangotesting.network.AuthApi
import com.example.mangotesting.network.responses.CheckAuthCodeResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}