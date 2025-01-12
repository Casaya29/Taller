package com.ivandy.taller.data.api

import com.ivandy.taller.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val apiService : ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}