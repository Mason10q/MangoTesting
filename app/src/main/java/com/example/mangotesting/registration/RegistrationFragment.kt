package com.example.mangotesting.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mangotesting.MainComponent
import com.example.mangotesting.R
import com.example.mangotesting.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private val viewModel by viewModels<RegisterViewModel>()

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

        binding.registerBtn.setOnClickListener {
            if (binding.registrationNameEdit.text.isNotEmpty() && binding.usernameEdit.text.isNotEmpty()) {
                viewModel.registrate(
                    phone.toString(),
                    binding.usernameEdit.text.toString(),
                    binding.usernameEdit.text.toString()
                )
            }
        }

        return binding.root
    }

}