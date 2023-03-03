package com.example.binproject

data class CardDetails(
    val number: NumberDetails?,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryDetails,
    val bank: BankDetails
)

data class NumberDetails(
    val length: Int,
    val luhn: Boolean
)

data class CountryDetails(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
)

data class BankDetails(
    val name: String,
    val url: String,
    val phone: String,
    val city: String?
)





