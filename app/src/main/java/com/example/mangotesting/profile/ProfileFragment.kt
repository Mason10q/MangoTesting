package com.example.mangotesting.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.mangotesting.MainComponent
import com.example.mangotesting.databinding.FragmentProfileBinding
import com.example.mangotesting.entities.Profile

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

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.profileData.observe(viewLifecycleOwner){
            bindView(binding, it)
        }

        return binding.root
    }


    private fun bindView(binding: FragmentProfileBinding, item: Profile){
        with(binding){
            name.text = item.name
            username.text = item.username
            phone.text = item.phone
            birthday.text = item.birthday
            city.text = item.city
            zodiacSign.text = item.zodiacSign
        }
    }

}