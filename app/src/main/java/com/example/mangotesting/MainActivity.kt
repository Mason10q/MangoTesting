package com.example.mangotesting

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mangotesting.auth.AuthViewModel

class MainActivity : AppCompatActivity(), OpenProfile {

    private val tokensSp by lazy { getPreferences(MODE_PRIVATE) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.
        findFragmentById(R.id.host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun openProfile(refreshToken: String, accessToken: String) {
        val editor = tokensSp.edit()

        editor.putString(REFRESH_TOKEN_KEY, refreshToken)
        editor.putString(ACCESS_TOKEN_KEY, accessToken)

        editor.apply()

        navController.navigate(R.id.profileFragment)
    }
}