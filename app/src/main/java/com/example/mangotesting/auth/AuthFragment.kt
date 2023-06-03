package com.example.mangotesting.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mangotesting.MainComponent
import com.example.mangotesting.R
import com.example.mangotesting.databinding.FragmentAuthBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class AuthFragment : Fragment() {

    private val countryCodes = arrayOf(
        CountryCode(R.drawable.russia, "+7", " (___) ___-__-__"),
        CountryCode(R.drawable.ukrain, "+380", " (__) ___-__-__")
    )

    private var currentSpinPos = 0

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAuthBinding.inflate(inflater)

        with(MainComponent.init()){
            inject(viewModel)
        }

        binding.countryCodeSpinner.adapter =
            activity?.let { CountryCodeAdapter(it, R.layout.item_country_code, countryCodes) }

        val formatWatcher = MaskFormatWatcher(countryCodes[0].mask)
        formatWatcher.installOn(binding.authNumberEdit)

        binding.countryCodeSpinner.onItemSelectedListener =
            setOnItemSelected{ _: AdapterView<*>, _: View, pos: Int, _: Long ->
                formatWatcher.setMask(countryCodes[pos].mask)
                currentSpinPos = pos
            }

        binding.authNumberEdit.addTextChangedListener(SimpleTextWatcher {
            binding.authBtn.isEnabled = (it.length == countryCodes[currentSpinPos].mask.size)
        })

        binding.authBtn.setOnClickListener {
            val number = countryCodes[currentSpinPos].code + binding.authNumberEdit.text
            viewModel.sendAuthCode(number)

            if(viewModel.sendAuthResult.value == true){
                Log.d("tag", number)
            }
        }

        return binding.root
    }

    private fun setOnItemSelected(l: SimpleSelectListener): SimpleSelectListener = l
}