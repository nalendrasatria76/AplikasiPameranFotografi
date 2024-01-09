package com.example.aplikasipameranfotografi.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Visibility
import androidx.compose.material3.icons.filled.VisibilityOff
import androidx.compose.material3.ui.*
import androidx.compose.material3.ui.graphics.Color
import androidx.compose.material3.ui.layout.ContentScale
import androidx.compose.material3.ui.platform.LocalContext
import androidx.compose.material3.ui.platform.LocalDensity
import androidx.compose.material3.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.material3.ui.res.painterResource
import androidx.compose.material3.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplikasipameranfotografi.R
import com.example.aplikasipameranfotografi.ui.theme.AplikasiPameranFotografiTheme

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

data class Exhibition(val name: String, val location: String, val date: String, val imageResId: Int)

@Composable
fun FotografiApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "exhibitionList") {
        composable("exhibitionList") {
            ExhibitionListScreen(navController)
        }
        composable("exhibitionDetail/{exhibitionName}") { backStackEntry ->
            ExhibitionDetailScreen(
                navController,
                backStackEntry.arguments?.getString("exhibitionName")
            )
        }
        composable("registrationForm/{exhibitionName}") { backStackEntry ->
            RegistrationFormScreen(
                navController,
                backStackEntry.arguments?.getString("exhibitionName")
            )
        }
    }
}

@Composable
fun ExhibitionListScreen(navController: NavHostController) {
    val exhibitions = listOf(
        Exhibition("Nature in Focus", "City Exhibition Hall", "2022-05-15", R.drawable.exhibition_nature),
        Exhibition("Urban Life", "Downtown Gallery", "2022-06-20", R.drawable.exhibition_urban),
        Exhibition("Portraits of the World", "Metro Museum", "2022-07-25", R.drawable.exhibition_portraits)
    )

    var selectedExhibition by remember { mutableStateOf<Exhibition?>(null) }

    Column {
        Text(
            text = "Upcoming Exhibitions",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(exhibitions) { exhibition ->
                ExhibitionItem(exhibition, selectedExhibition == exhibition) {
                    navController.navigate("exhibitionDetail/${exhibition.name}")
                }
            }
        }
    }
}

@Composable
fun ExhibitionItem(
    exhibition: Exhibition,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Column {
            Image(
                painter = painterResource(id = exhibition.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = exhibition.name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Location: ${exhibition.location}\nDate: ${exhibition.date}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
                )
            }
        }
    }
}

@Composable
fun ExhibitionDetailScreen(navController: NavHostController, exhibitionName: String?) {
    // ... (kode ExhibitionDetailScreen sebelumnya)

    Text(
        text = "Exhibition Detail",
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(16.dp)
    )

    ExhibitionItem(exhibition, false) { /* no action on click */ }

    Button(
        onClick = {
            navController.navigate("registrationForm/{exhibitionName}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Register for Exhibition")
    }
}

@Composable
fun RegistrationFormScreen(navController: NavHostController, exhibitionName: String?) {
    // ... (kode RegistrationFormScreen sebelumnya)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Register for $exhibitionName",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )

        // ... (kode RegistrationFormScreen sebelumnya)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("exhibitionList") {
                        popUpTo("exhibitionList") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {
                    // Simpan data atau kirim ke server
                    navController.navigate("exhibitionList") {
                        popUpTo("exhibitionList") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text("Submit")
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
