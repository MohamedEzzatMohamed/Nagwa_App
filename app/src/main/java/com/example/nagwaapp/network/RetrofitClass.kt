package com.example.nagwaapp.network

import com.example.nagwaapp.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * a Retrofit object to contain the Base url and the factory so the data converter is Gson
 * and a val api interface to use the api functions
 */
object RetrofitClass {

    private val retrofit by lazy  {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}