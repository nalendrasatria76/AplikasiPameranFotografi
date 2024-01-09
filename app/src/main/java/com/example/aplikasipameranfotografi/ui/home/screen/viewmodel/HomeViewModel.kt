package com.example.aplikasipameranfotografi.ui.home.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    // Contoh properti ViewModel
    private var statusText: String = "Status Default"
    private var isOnline: Boolean = false

    // Fungsi untuk mengubah status dan mencetak pesan
    fun toggleOnlineStatus() {
        viewModelScope.launch {
            isOnline = !isOnline
            statusText = if (isOnline) "Online" else "Offline"
            println("Status: $statusText")
        }
    }

    // Fungsi untuk melakukan tindakan async (contoh: penundaan selama 2 detik)
    fun performAsyncAction() {
        viewModelScope.launch {
            println("Start performing async action")
            delay(2000)
            println("Async action completed")
        }
    }
}
