package com.example.aplikasipameranfotografi.navigation

import androidx.navigation.NavHostController

sealed class NavigationDestinations {
    object ExhibitionList : NavigationDestinations()
    data class ExhibitionDetail(val exhibitionName: String) : NavigationDestinations()
    data class RegistrationForm(val exhibitionName: String) : NavigationDestinations()

    fun navigate(navController: NavHostController, exhibitionName: String? = null) {
        when (this) {
            is ExhibitionList -> navController.navigate("exhibitionList")
            is ExhibitionDetail -> exhibitionName?.let { navController.navigate("exhibitionDetail/$it") }
            is RegistrationForm -> exhibitionName?.let { navController.navigate("registrationForm/$it") }
        }
    }
}
