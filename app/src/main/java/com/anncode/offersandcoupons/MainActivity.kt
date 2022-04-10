package com.anncode.offersandcoupons

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.anncode.offersandcoupons.model.ApiAdapter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        /**
         * VISTA
         * Se llama al RecyclerView de la vista en el main
         * Se le agrega como variable para hacerle cambios
         * en el controlador, además se crea una Array de
         * cupones
         */
        val rvCoupons: RecyclerView = findViewById(R.id.rvCoupons) //UI
        rvCoupons.layoutManager = LinearLayoutManager(this)
        val coupons = ArrayList<Coupon>()


        /**
         * CONTROLADOR
         * Se hace la llamada de la API, creando primero una
         * clase del Adaptador, y despues del servicio
         * para obtener el método GET
         *
         * Con la llamada se hacen dos métodos, uno en caso
         * de fallo y otro en respuesta por parte de la API
         */
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                /**
                 * CONTROLADOR
                 * En caso de respuesta por parte de la API, por cada elemento recibido
                 * se agrega al Array de cupones, cada elemento cupon con su información
                 *
                 * VISTA
                 * Con la ayuda de un adaptador, se agrega cada cupón al RecycleView
                 */
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    coupons.add(coupon)
                }

                var adaptador=RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
                rvCoupons.adapter = adaptador

            }

        })

    }
}
