package com.anncode.offersandcoupons.model

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiAdapter {
    /**
     * Este es el corazón del modelo, es donde todo de la API
     * es manejado, pero se mantiene simple, todo este código es
     * unicamente para crear la conexión con la API
     */

    val apiKey = "95e2fcbc843ca8db898d5d2ac47ad1fd"
    val urlApi = "https://feed.linkmydeals.com/"

    /**
     * Si se desea controlar la API, entrar en linkmydeals y crearse una cuenta
     * se le dará su key la cual solo sustituye, el resto de cambios, como que
     * tiendas mostrar o el manejo de costos se hace desde la página
     */

    fun getClientService(): ApiService {
        val authInterceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder()
                .addQueryParameter("API_KEY", apiKey)
                .addQueryParameter("format", "json")
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()

            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor).build()

        val gson=GsonBuilder()
            .setLenient()
            .create();

        val retrofit = Retrofit.Builder()
            .baseUrl(urlApi)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return  retrofit.create(ApiService::class.java)
    }

}