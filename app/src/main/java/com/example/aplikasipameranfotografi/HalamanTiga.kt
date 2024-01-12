package com.example.aplikasipameranfotografi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.aplikasipameranfotografi.data.OrderUIState

enum class PaymentOption {
    BANK_BRI,
    BANK_BCA,
    BANK_MANDIRI,
    OVO,
    DANA,
    GOPAY
}

@Composable
fun HalamanTiga(
    orderUIState: OrderUIState,  // Add this parameter to receive orderUIState
    onPaymentOptionSelected: (PaymentOption) -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text("Pilih Metode Pembayaran", style = MaterialTheme.typography.displayLarge)

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        PaymentOptionItem(
            option = PaymentOption.BANK_BRI,
            onPaymentOptionSelected = onPaymentOptionSelected
        )
        PaymentOptionItem(
            option = PaymentOption.BANK_BCA,
            onPaymentOptionSelected = onPaymentOptionSelected
        )
        PaymentOptionItem(
            option = PaymentOption.BANK_MANDIRI,
            onPaymentOptionSelected = onPaymentOptionSelected
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        PaymentOptionItem(
            option = PaymentOption.OVO,
            onPaymentOptionSelected = onPaymentOptionSelected
        )
        PaymentOptionItem(
            option = PaymentOption.DANA,
            onPaymentOptionSelected = onPaymentOptionSelected
        )
        PaymentOptionItem(
            option = PaymentOption.GOPAY,
            onPaymentOptionSelected = onPaymentOptionSelected
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /* Handle payment confirmation */ }
            ) {
                Text(stringResource(R.string.confirm_payment))
            }
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    }
}

@Composable
fun PaymentOptionItem(
    option: PaymentOption,
    onPaymentOptionSelected: (PaymentOption) -> Unit
) {
    Row(
        modifier = Modifier
            .selectable(selected = false) {
                onPaymentOptionSelected(option)
            }
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = false, onClick = { onPaymentOptionSelected(option) })
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
        Text(when (option) {
            PaymentOption.BANK_BRI -> "Bank BRI"
            PaymentOption.BANK_BCA -> "Bank BCA"
            PaymentOption.BANK_MANDIRI -> "Bank Mandiri"
            PaymentOption.OVO -> "OVO"
            PaymentOption.DANA -> "DANA"
            PaymentOption.GOPAY -> "GoPay"
        })
    }
}
