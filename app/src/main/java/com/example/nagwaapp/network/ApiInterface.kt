package com.example.nagwaapp.network

import com.example.nagwaapp.models.Material
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit interface to implement the api functions
 */
interface ApiInterface {


    /**
     * get function to request the list of material which includes pdf, and videos
     */
    @GET("movies")
    suspend fun getMaterial(): Response<ArrayList<Material>>
}