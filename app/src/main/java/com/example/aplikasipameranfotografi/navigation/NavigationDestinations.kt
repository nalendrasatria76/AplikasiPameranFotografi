package com.example.aplikasipameranfotografi.navigation

sealed class NavigationDestinations {
    object ExhibitionList : NavigationDestinations()
    object ExhibitionDetail : NavigationDestinations()
    object RegistrationForm : NavigationDestinations()
    // Tambahkan destinasi lain sesuai kebutuhan aplikasi Anda
}
