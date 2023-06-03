package com.example.mangotesting

import com.example.mangotesting.auth.AuthFragment
import com.example.mangotesting.auth.AuthViewModel
import com.example.mangotesting.network.AuthModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class])
interface MainComponent {

    fun inject(viewModel: AuthViewModel)
    companion object{
        fun init(): MainComponent = DaggerMainComponent.builder()
            .authModule(AuthModule())
            .build()
    }
}
