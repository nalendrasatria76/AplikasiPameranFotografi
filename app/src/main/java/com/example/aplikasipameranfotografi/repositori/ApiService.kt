package com.example.aplikasipameranfotografi.network

import com.example.aplikasipameranfotografi.service_api.PenggunaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

class ApiService private constructor(retrofit: Retrofit) {

    val penggunaService: PenggunaService by lazy { retrofit.create(PenggunaService::class.java) }

    companion object {

        private const val BASE_URL = "https://example.com/api/" // Ganti dengan URL API sesuai kebutuhan

        private val contentType = "application/json".toMediaType()

        // Singleton instance
        @Volatile
        private var instance: ApiService? = null

        fun create(): ApiService {
            return instance ?: synchronized(this) {
                instance ?: buildRetrofit().let {
                    ApiService(it).also { serviceInstance -> instance = serviceInstance }
                }
            }
        }

        private fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(Json.asConverterFactory(contentType))
                .build()
        }
    }
}
