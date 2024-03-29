package com.example.aplikasipameranfotografi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiPameranFotografiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FotografiApp()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FotografiAppPreview() {
    AplikasiPameranFotografiTheme {
        FotografiApp()
    }
}


@Composable
fun AplikasiPameranFotografiTheme(content: @Composable () -> Unit) {
    // Add your theme settings here if needed
    content()
}
