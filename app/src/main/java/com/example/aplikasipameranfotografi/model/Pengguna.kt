package com.example.aplikasipameranfotografi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pengguna(
    @SerialName("id") val id: Int,
    @SerialName("nama") val nama: String,
    @SerialName("alamat") val alamat: String,
    @SerialName("telpon") val telpon: String,
)
