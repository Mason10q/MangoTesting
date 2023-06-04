package com.example.mangotesting.registration

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.mangotesting.MainComponent
import com.example.mangotesting.OpenProfile
import com.example.mangotesting.R
import com.example.mangotesting.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var openProfile: OpenProfile

    override fun onAttach(context: Context) {
        super.onAttach(context)

        openProfile = context as OpenProfile
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegistrationBinding.inflate(inflater)

        with(MainComponent.init()) {
            inject(viewModel)
        }

        val phone = arguments?.getString("PHONE")

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }

        binding.registerBtn.setOnClickListener {
            if (binding.registrationNameEdit.text.isNotEmpty() && binding.usernameEdit.text.isNotEmpty()) {
                viewModel.registrate(
                    phone.toString(),
                    binding.registrationNameEdit.text.toString(),
                    binding.usernameEdit.text.toString()
                )

                viewModel.registerResult.observe(viewLifecycleOwner){
                    it?.let{ openProfile.openProfile(it.refreshToken, it.accessToken) }
                }

            }
        }

        return binding.root
    }

}