package com.example.mangotesting

import com.example.mangotesting.auth.AuthFragment
import com.example.mangotesting.auth.AuthViewModel
import com.example.mangotesting.network.AuthModule
import com.example.mangotesting.registration.RegisterViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class])
interface MainComponent {

    fun inject(viewModel: AuthViewModel)
    fun inject(viewModel: RegisterViewModel)
    companion object{
        fun init(): MainComponent = DaggerMainComponent.builder()
            .authModule(AuthModule())
            .build()
    }
}
