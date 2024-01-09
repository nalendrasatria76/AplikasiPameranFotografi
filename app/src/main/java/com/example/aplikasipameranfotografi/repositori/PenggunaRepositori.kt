package com.example.aplikasipameranfotografi.repositori

import com.example.aplikasipameranfotografi.model.Pengguna
import com.example.aplikasipameranfotografi.service_api.PenggunaService

class PenggunaRepository(private val penggunaService: PenggunaService) {

    // Mendapatkan informasi pengguna berdasarkan ID
    suspend fun getPenggunaById(id: Int): Pengguna {
        return penggunaService.getPenggunaById(id)
    }

    // Tambahkan metode lain sesuai kebutuhan, seperti menambahkan, mengupdate, atau menghapus pengguna.
}
