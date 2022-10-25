package com.ptg.llas_pedidos.screens.screens_master

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ptg.llas_pedidos.data.CreateNewItem
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme
import com.ptg.llas_pedidos.ui.theme.Pink

@Composable
fun CardapioScreen(
    clik: () -> Unit
) {
    val context =  LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                onClick = { clik() }
            ) {
                Text(text = "Cadastrar novo item")
            }
        }
    }

    LlasPedidosTheme {

    }
}





//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Cyan),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "CARDÁPIO",
//            fontSize = MaterialTheme.typography.h3.fontSize,
//            fontWeight = FontWeight.Bold,
//            color = Color.White
//        )
//    }



//@Preview(showBackground = true)
//@Composable
//fun CardapioScreenPreview() {
//    CardapioScreen()
//}