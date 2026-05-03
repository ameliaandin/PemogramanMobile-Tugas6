package com.utama.aplikasiloginsederhana

import java.text.NumberFormat
import java.util.Locale

data class Event(
    val id: Int,
    val name: String,
    val date: String,
    val location: String,
    val price: Int,
    val isRegistered: Boolean = false
) {
    fun getFormattedPrice(): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(price.toLong())
    }
}