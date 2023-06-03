package com.example.mangotesting.auth

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mangotesting.databinding.ItemCountryCodeBinding

class CountryCodeAdapter(
    context: Context,
    layout: Int,
    private val countryCodes: Array<CountryCode>
) : ArrayAdapter<CountryCode>(context, layout, countryCodes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        getCustomView(position,  parent)


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        getCustomView(position, parent)


    private fun getCustomView(position: Int, parent: ViewGroup): View{
        val binding = ItemCountryCodeBinding.inflate(LayoutInflater.from(parent.context))
        binding.numberCode.text = countryCodes[position].code
        binding.countryFlag.setImageResource(countryCodes[position].image)

        return binding.root
    }

}