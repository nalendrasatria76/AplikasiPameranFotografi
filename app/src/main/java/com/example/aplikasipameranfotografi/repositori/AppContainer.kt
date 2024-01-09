package com.example.aplikasipameranfotografi.repositori

import android.content.Context
import com.example.aplikasipameranfotografi.network.ApiService

class AppContainer(context: Context) {

    // Singleton instance
    companion object {
        private var instance: AppContainer? = null

        fun getInstance(context: Context): AppContainer {
            return instance ?: synchronized(this) {
                instance ?: AppContainer(context).also { instance = it }
            }
        }
    }

    // Dependencies
    private val apiService: ApiService by lazy { ApiService.create() }
    private val exhibitionRepository: ExhibitionRepository by lazy { ExhibitionRepository() }

    // Provide ExhibitionRepository
    fun provideExhibitionRepository(): ExhibitionRepository {
        return exhibitionRepository
    }

    // You can add more methods to provide other repositories, services, or dependencies as needed.
}
