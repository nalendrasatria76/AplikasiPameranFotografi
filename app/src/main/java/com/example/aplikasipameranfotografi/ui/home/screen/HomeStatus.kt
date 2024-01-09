package com.example.aplikasipameranfotografi.ui.home.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aplikasipameranfotografi.ui.theme.AplikasiPameranFotografiTheme

@Composable
fun HomeStatus() {
    var statusText by remember { mutableStateOf("Status Default") }
    var isOnline by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Status: $statusText",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Switch(
            checked = isOnline,
            onCheckedChange = {
                isOnline = it
                statusText = if (isOnline) "Online" else "Offline"
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeStatusPreview() {
    AplikasiPameranFotografiTheme {
        HomeStatus()
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeStatusPreviewDark() {
    AplikasiPameranFotografiTheme(darkTheme = true) {
        HomeStatus()
    }
}
