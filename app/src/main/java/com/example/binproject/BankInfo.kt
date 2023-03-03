package com.example.binproject

class BankInfo(
    var name: String,
    var url: String,
    var phone: String,
    var city: String
) {
    fun body(): String {
        val text = "$name\n" +
                   "$url\n" +
                   "$phone\n" +
                   "$city\n"

        return text
    }
}
