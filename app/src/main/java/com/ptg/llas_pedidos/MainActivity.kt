package com.ptg.llas_pedidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LlasPedidosTheme {
                MainScreen()
            }
        }
    }
}
