package com.ptg.llas_pedidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ptg.llas_pedidos.detail.DetailViewModel
import com.ptg.llas_pedidos.home.HomeViewModel
import com.ptg.llas_pedidos.login.LoginViewModel
import com.ptg.llas_pedidos.ui.theme.BgColor
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
            val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
            val detailViewModel = viewModel(modelClass = DetailViewModel::class.java)
            LlasPedidosTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Cyan,
                        darkIcons = true
                    )
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BgColor
                ) {
                    Navigation(
                        loginViewModel = loginViewModel,
                        detailViewModel = detailViewModel,
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }
}
