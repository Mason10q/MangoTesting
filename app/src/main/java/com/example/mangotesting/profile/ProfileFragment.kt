package com.example.mangotesting.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mangotesting.MainComponent
import com.example.mangotesting.R
import com.example.mangotesting.databinding.FragmentProfileBinding

class ProfileFragment(): Fragment(){

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater)

        MainComponent.init().inject(viewModel)

        viewModel.getProfileData()

        viewModel.profileData.observe(viewLifecycleOwner){
            with(binding){
                name.text = it.name
                username.text = it.username
                phone.text = it.phone
                birthday.text = it.birthday
                city.text = it.city
                zodiacSign.text = it.zodiacSign
            }
        }

        return binding.root
    }

}