package com.example.aplikasipameranfotografi

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplikasipameranfotografi.data.OrderUIState
import com.example.aplikasipameranfotografi.data.SumberData

enum class FotografiApp {
    Home,
    Jenis,
    Summary,
    Tiga,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FotografiAppAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = navigasiUp) {
                    Icon(
                        painterResource(R.drawable.arrow_back),
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FotografiApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            FotografiAppAppBar(bisaNavigasiBack = false, navigasiUp = { /*TODO*/ })
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = FotografiApp.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = FotografiApp.Home.name) {
                HalamanHome(onNextButtonClicked = {
                    navController.navigate(FotografiApp.Jenis.name)
                })
            }
            composable(route = FotografiApp.Jenis.name) {
                val context = LocalContext.current
                HalamanSatu(
                    pilihanRasa = SumberData.Jenis.map { id -> context.resources.getString(id) },
                    onSelectionChanged = { viewModel.setRasa(it) },
                    onConfirmButtonClicked = { jumlahOrder ->
                        viewModel.setJumlah(jumlahOrder)
                        // Navigate to HalamanTiga when the confirmation button is clicked
                        navController.navigate(FotografiApp.Summary.name)
                    },
                    onNextButtonClicked = { /* Not needed in this case */ },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToHome(
                            viewModel,
                            navController
                        )
                    }
                )
            }
            composable(route = FotografiApp.Summary.name) {
                HalamanDua(
                    orderUIState = uiState,
                    onNextButtonClicked = {
                        // Navigate to HalamanTiga when the next button is clicked
                        navController.navigate("${FotografiApp.Tiga.name}/${uiState.jumlah}/${uiState.Jenis}")
                    },
                    onConfirmButtonClicked = {
                        // Navigate to HalamanTiga when the confirm button is clicked
                        navController.navigate("${FotografiApp.Tiga.name}/${uiState.jumlah}/${uiState.Jenis}")
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToRasa(navController) }
                )
            }
            composable(route = FotografiApp.Tiga.name + "/{jumlah}/{jenis}") { backStackEntry ->
                val jumlah = backStackEntry.arguments?.getString("jumlah")?.toInt() ?: 0
                val jenis = backStackEntry.arguments?.getString("jenis") ?: ""
                val orderUIState = OrderUIState(jumlah = jumlah, Jenis = jenis)

                HalamanTiga(
                    orderUIState = orderUIState,
                    onPaymentOptionSelected = { /* Handle payment option selected */ },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToHome(
                            viewModel,
                            navController
                        )
                    }
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToHome(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(FotografiApp.Home.name, inclusive = false)
}

private fun cancelOrderAndNavigateToRasa(
    navController: NavHostController
) {
    navController.popBackStack(FotografiApp.Jenis.name, inclusive = false)
}
