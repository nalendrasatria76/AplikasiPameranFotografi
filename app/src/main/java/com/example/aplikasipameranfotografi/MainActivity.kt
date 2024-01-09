package com.example.aplikasipameranfotografi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Event
import androidx.compose.material3.icons.filled.Person
import androidx.compose.material3.icons.filled.Place
import androidx.compose.material3.icons.filled.Send
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
import androidx.compose.material3.ui.unit.dp
import androidx.compose.material3.utils.toRoundedCorner
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
    val exhibitions = listOf(
        Exhibition("Nature in Focus", "City Exhibition Hall", "2022-05-15", R.drawable.exhibition_nature),
        Exhibition("Urban Life", "Downtown Gallery", "2022-06-20", R.drawable.exhibition_urban),
        Exhibition("Portraits of the World", "Metro Museum", "2022-07-25", R.drawable.exhibition_portraits)
    )

    var selectedExhibition by remember { mutableStateOf<Exhibition?>(null) }
    var registrationFormVisible by remember { mutableStateOf(false) }

    if (registrationFormVisible) {
        RegistrationForm(
            onSubmitted = {
                // Proses pendaftaran
                registrationFormVisible = false
                selectedExhibition = null
            },
            onCancel = { registrationFormVisible = false }
        )
    } else {
        FotografiAppContent(exhibitions, selectedExhibition) { exhibition ->
            selectedExhibition = exhibition
            registrationFormVisible = true
        }
    }
}

@Composable
fun FotografiAppContent(
    exhibitions: List<Exhibition>,
    selectedExhibition: Exhibition?,
    onExhibitionSelected: (Exhibition) -> Unit
) {
    Column {
        Text(
            text = "Upcoming Exhibitions",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(exhibitions) { exhibition ->
                ExhibitionItem(exhibition, selectedExhibition == exhibition) {
                    onExhibitionSelected(exhibition)
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
fun RegistrationForm(
    onSubmitted: () -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val density = LocalDensity.current.density

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Register for Exhibition",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Toggle password visibility",
                        tint = Color.Gray
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onDone = {
                onSubmitted()
                LocalSoftwareKeyboardController.current?.hide()
            }),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onCancel,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Cancel")
            }

            Button(
                onClick = onSubmitted,
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
