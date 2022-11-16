package com.ptg.llas_pedidos.ui.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ptg.llas_pedidos.ui.theme.Pink

@Composable
fun CreateNewItem() {
    val db = Firebase.firestore
    val context = LocalContext.current

    var itemName by remember { mutableStateOf("") }
    var itemDescription by remember { mutableStateOf("") }
    var check by remember { mutableStateOf<Boolean>(false) }
    var report by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Adicionar um novo item",
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            placeholder = {
                Text(
                    text = "Insira o nome do item",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = itemDescription,
            onValueChange = { itemDescription = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            placeholder = {
                Text(
                    text = "Descrição do item",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row() {
            OutlinedButton(
                onClick = {
                    if (itemName.isNotEmpty() && itemDescription.isNotEmpty()) {
                        // Create a new user with a first and last name
                        val item = hashMapOf(
                            "nome" to itemName,
                            "descricao" to itemDescription
                        )

                        // Add a new document with a generated ID
                        db.collection("itens")
                            .add(item)
                            .addOnSuccessListener { documentReference ->
                                Toast.makeText(
                                    context,
                                    "Item criado com sucesso, com o ID: ${documentReference.id}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(context, "Erro ao adicionar o item", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {
                Text(text = "Adicionar", fontSize = 16.sp, color = Color.Black)
            }

            OutlinedButton(
                onClick = {
                    val data = StringBuffer()
                    db.collection("itens")
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                data.append("ID = " + document.id + "\n")
                                data.append("Nome = " + document.get("nome") + "\n")
                                data.append("Descrição = " + document.get("descricao") + "\n")
                                data.append("==============================================\n")
                            }

                            check = true
                            report = data.toString()
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                }
            ) {
                Text(text = "Mostrar", fontSize = 16.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {
            OutlinedButton(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Atualizar", fontSize = 16.sp, color = Color.Black)
            }
            OutlinedButton(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Deletar", fontSize = 16.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(
                    state = scrollState,
                    orientation = Orientation.Vertical
                )
        ) {
            AnimatedVisibility(visible = check, Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier,
                    text = report,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }

    }

}



@Preview
@Composable
fun CreateNewItemScreenPreview() {
    CreateNewItem()
}