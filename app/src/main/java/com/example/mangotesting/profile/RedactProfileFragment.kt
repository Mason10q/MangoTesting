package com.example.mangotesting.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mangotesting.MainComponent
import com.example.mangotesting.PROFILE_KEY
import com.example.mangotesting.R
import com.example.mangotesting.databinding.FragmentProfileBinding
import com.example.mangotesting.databinding.FragmentRedactProfileBinding
import com.example.mangotesting.entities.Profile

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class RedactProfileFragment : Fragment() {

    private val binding by lazy { FragmentRedactProfileBinding.inflate(layoutInflater) }
    private val viewModel: ProfileViewModel by viewModels()
    private val profile: Profile? by lazy { arguments?.getSerializable(PROFILE_KEY, Profile::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        MainComponent.init().inject(viewModel)

        bindView(profile ?: Profile())

        binding.saveBtn.setOnClickListener {
            viewModel.updateProfile(getProfileFromView())
            findNavController().navigate(R.id.profileFragment)
            Thread.sleep(2000)
        }

        return binding.root
    }

    private fun bindView(item: Profile) {
        with(binding) {
            name.text = item.name
            username.text = item.username
            phone.text = item.phone
            city.setText(item.city)
            description.setText(item.description)
        }
    }

    private fun getProfileFromView(): Profile {
        return with(binding) {
            Profile(
                name.text.toString(),
                username.text.toString(),
                phone.text.toString(),
                if(year.text.isNotEmpty()) "${year.text}-${mounth.text}-${day.text}" else profile?.birthday.toString(),
                city.text.toString(),
                description.text.toString()
            )
        }
    }

}