package com.example.mangotesting.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mangotesting.MainComponent
import com.example.mangotesting.R
import com.example.mangotesting.databinding.FragmentCodeCheckBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class CodeCheckFragment : Fragment() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCodeCheckBinding.inflate(inflater)

        with(MainComponent.init()) {
            inject(viewModel)
        }

        val phone = arguments?.getString("PHONE")

        val formatWatcher =
            MaskFormatWatcher(
                MaskImpl.createTerminated(
                    UnderscoreDigitSlotsParser()
                        .parseSlots("______")
                )
            )

        formatWatcher.installOn(binding.smsCodeEdit)

        binding.smsCodeEdit.addTextChangedListener(SimpleTextWatcher {
            binding.confirmCodeBtn.isEnabled = (it.length == formatWatcher.mask.size)
        })

        binding.confirmCodeBtn.setOnClickListener {
            viewModel.checkAuthCode(phone.toString(), binding.smsCodeEdit.text.toString())

            val answer = viewModel.checkAuthResult.value

            if (answer?.isUserExists == true) {

            } else {
                findNavController().navigate(R.id.registrationFragment, Bundle().apply {
                    putString("PHONE", phone)
                })
            }
        }

        return binding.root
    }

}