package com.example.binproject

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class AppRepository() {

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(
            OkHttpClient.Builder().build()
        ).build()

    suspend fun getBankInfo(bin: String): CardDetails {

        val bankClient = retrofitBuilder.create(BankApi::class.java)
        try {
            return bankClient.getBankInfo("https://lookup.binlist.net/${bin}")
        } catch (error: Throwable) {

            throw error
        }
    }
}

interface BankApi {
    @GET()
    suspend fun getBankInfo(@Url url: String): CardDetails
}