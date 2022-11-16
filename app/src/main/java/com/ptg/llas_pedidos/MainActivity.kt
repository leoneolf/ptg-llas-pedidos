package com.ptg.llas_pedidos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.ptg.llas_pedidos.detail.DetailViewModel
import com.ptg.llas_pedidos.home.HomeViewModel
import com.ptg.llas_pedidos.login.LoginViewModel
import com.ptg.llas_pedidos.navigation.RootNavigationGraph
import com.ptg.llas_pedidos.ui.components.SingInButton
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
            val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
            val detailViewModel = viewModel(modelClass = DetailViewModel::class.java)
            LlasPedidosTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
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

//class MainActivity : ComponentActivity() {
//
//    companion object {
//        const val RC_SING_IN = 100
//    }
//
//    private lateinit var mAuth: FirebaseAuth
//    private lateinit var googleSingInClient: GoogleSignInClient
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        //Instância do Firebase Auth
//        mAuth = FirebaseAuth.getInstance()
//
//        //Configurar a conexão com o Google
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        googleSingInClient = GoogleSignIn.getClient(this, gso)
//
//        setContent {
//            LlasPedidosTheme {
//
//                if (mAuth.currentUser == null) {
//                    SingInButton {
//                        singIn()
//                    }
//                } else {
//                    val user: FirebaseUser = mAuth.currentUser!!
//                    singOut()
//                }
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Retorna resultado da criação do intent para o GoogleSingInApi.getSingInIntent()
//        if (requestCode == RC_SING_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            val exception = task.exception
//            if (task.isSuccessful) {
//                try {
//                    //Se a conexão com o Google foi realizada com sucesso, faz a autenticação com o Firebase
//                    val account = task.getResult(ApiException::class.java)!!
//                    firebaseAuthWithGoogle(account.idToken!!)
//                } catch (e: Exception) {
//                    //Falha na conexão com o Google
//                    Log.d("Entrar", "Falha na conexão com o Google")
//                }
//            } else {
//                Log.d("Entrar", exception.toString())
//            }
//        }
//
//    }
//
//    // Fazendo a autenticação com o Firebase
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    //Logado com sucesso
//                    Toast.makeText(this, "Logado com sucesso!", Toast.LENGTH_SHORT).show()
//                } else {
//                    //Error ao logar
//                    Toast.makeText(this, "Erro ao logar!", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//
//    private fun singIn() {
//        val signInClient = googleSingInClient.signInIntent
//        startActivityForResult(signInClient, RC_SING_IN)
//        setContent {
//            LlasPedidosTheme() {
//                RootNavigationGraph(navController = rememberNavController())
//            }
//        }
//    }
//
//    private fun singOut() {
//        //Pegar a conta do Google
//        val googleSignInClient: GoogleSignInClient
//
//        //Configurando a entrada no Google
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        googleSignInClient = GoogleSignIn.getClient(this, gso)
//
//        //Saindo de todas as contas
//        mAuth.signOut()
//        googleSignInClient.signOut()
//            .addOnSuccessListener {
////                Toast.makeText(this, "Desconectado com sucesso!", Toast.LENGTH_SHORT).show()
//                setContent {
//                    LlasPedidosTheme {
//                        SingInButton {
//                            singIn()
//                        }
//                    }
//                }
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Falha ao desconectar!", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LlasPedidosTheme {}
//}
