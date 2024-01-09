package com.example.aplikasipameranfotografi.service_api

import com.example.aplikasipameranfotografi.model.Pengguna
import retrofit2.http.GET
import retrofit2.http.Path

interface PenggunaService {

    @GET("pengguna/{id}")
    suspend fun getPenggunaById(@Path("id") id: Int): Pengguna

    // Tambahkan metode lain sesuai dengan kebutuhan, seperti POST, PUT, DELETE, dll.
}
