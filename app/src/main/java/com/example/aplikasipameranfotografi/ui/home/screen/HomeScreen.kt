package com.example.aplikasipameranfotografi.ui.home.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aplikasipameranfotografi.navigation.NavigationDestinations
import com.example.aplikasipameranfotografi.ui.theme.AplikasiPameranFotografiTheme

@Composable
fun HomeScreen(navigationDestinations: NavigationDestinations) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Selamat datang di Aplikasi Pameran Fotografi",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navigationDestinations.navigateToExhibitionList() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Lihat Pameran Fotografi")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    AplikasiPameranFotografiTheme {
        HomeScreen(
            navigationDestinations = object : NavigationDestinations {
                override fun navigateToExhibitionList() {
                    // Implement navigation logic
                }

                override fun navigateToExhibitionDetail(exhibitionName: String) {
                    // Implement navigation logic
                }

                override fun navigateToRegistrationForm(exhibitionName: String) {
                    // Implement navigation logic
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreviewDark() {
    AplikasiPameranFotografiTheme(darkTheme = true) {
        HomeScreen(
            navigationDestinations = object : NavigationDestinations {
                override fun navigateToExhibitionList() {
                    // Implement navigation logic
                }

                override fun navigateToExhibitionDetail(exhibitionName: String) {
                    // Implement navigation logic
                }

                override fun navigateToRegistrationForm(exhibitionName: String) {
                    // Implement navigation logic
                }
            }
        )
    }
}
