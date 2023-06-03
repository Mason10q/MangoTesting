package com.example.mangotesting

import com.example.mangotesting.network.AuthModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    companion object{
        fun init(): MainComponent = DaggerMainComponent.builder()
            .authModule(AuthModule())
            .build()
    }
}
