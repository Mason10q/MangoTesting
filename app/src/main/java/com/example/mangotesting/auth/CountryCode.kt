package com.example.mangotesting.auth

import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.slots.Slot

data class CountryCode(
    val image: Int = 0,
    val code: String = "",
    private val _mask: String = ""
){
    private val slots: Array<Slot> = UnderscoreDigitSlotsParser().parseSlots(_mask)
    val mask: MaskImpl =  MaskImpl.createTerminated(slots)
}