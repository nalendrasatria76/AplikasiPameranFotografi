package com.example.aplikasipameranfotografi

import androidx.lifecycle.ViewModel
import com.example.aplikasipameranfotografi.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
private const val HARGA_PER_ORANG = 50000

class OrderViewModel : ViewModel(){
    private val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()

    fun setJumlah(jmljenis:Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy (
                jumlah = jmljenis,
                harga = hitungHarga(jumlah = jmljenis)) }
    }

    fun setRasa(jenisPilihan: String) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(Jenis = jenisPilihan)
        }
    }

    fun resetOrder(){
        _stateUI.value = OrderUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ): String{
        val kalkulasiHarga = jumlah * HARGA_PER_ORANG

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }
}