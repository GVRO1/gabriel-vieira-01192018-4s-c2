package br.com.coolpon.continuada2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCachorro {
    @GET("/bandtec-api/cachorros/{id}")
    fun cachorroId(@Path("id") id: String?): Call<Cachorro>
}