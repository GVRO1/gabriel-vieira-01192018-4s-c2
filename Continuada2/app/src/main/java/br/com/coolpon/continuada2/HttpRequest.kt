package br.com.coolpon.continuada2

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRequest {

    fun criar(): ApiCachorro {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://5f861cfdc8a16a0016e6aacd.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        // recuperamos a implementação da interface com os EndPoints
        val api = retrofit.create(ApiCachorro::class.java)

        return api
    }
}