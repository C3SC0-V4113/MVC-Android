package com.anncode.offersandcoupons.model

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    /**
     * Si se manejara una API más compleja, aqui estarian más metodos
     * directos con la API, pero en este caso unicamente se tiene GET
     */
    @GET("getOffers/")
    fun getCoupons(): Call<JsonObject>
}