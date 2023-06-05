package com.example.mangotesting.profile

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mangotesting.MainComponent
import com.example.mangotesting.PROFILE_KEY
import com.example.mangotesting.R
import com.example.mangotesting.databinding.FragmentProfileBinding
import com.example.mangotesting.entities.Profile

class ProfileFragment: Fragment(){

    private val viewModel: ProfileViewModel by viewModels()
    private val binding by lazy{ FragmentProfileBinding.inflate(layoutInflater) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        MainComponent.init().inject(viewModel)

        viewModel.getProfileData()

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.profileData.observe(viewLifecycleOwner){
            bindView(it)
        }

        binding.redactBtn.setOnClickListener{
            findNavController().navigate(R.id.redactProfileFragment, Bundle().apply {
                putSerializable(PROFILE_KEY, viewModel.profileData.value)
            })
        }


        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindView(item: Profile){
        Log.d("tagg", item.toString())
        with(binding){
            name.text = item.name
            username.text = item.username
            phone.text = item.phone
            birthday.text = item.birthday
            city.text = item.city
            zodiacSign.text = item.zodiacSign
            description.text = item.description
        }
    }


    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}