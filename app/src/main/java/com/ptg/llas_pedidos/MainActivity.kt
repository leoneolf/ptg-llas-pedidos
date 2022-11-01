package com.ptg.llas_pedidos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.ptg.llas_pedidos.authetication.AdminAuth
import com.ptg.llas_pedidos.data.CreateNewItem
import com.ptg.llas_pedidos.navigation.RootNavigationGraph
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val RC_SING_IN = 100
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LlasPedidosTheme {
                RootNavigationGraph(navController = rememberNavController())
            }

        }

    }}
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
////        setContent {
////            LlasPedidosTheme {
////
////                if (mAuth.currentUser == null) {
////                    SingInButton {
////                        singIn()
////                    }
////                } else {
////                    val user: FirebaseUser = mAuth.currentUser!!
////                    ProfileScreen(
////                        profileImage = user.photoUrl!!,
////                        name = user.displayName!!,
////                        email = user.email!!,
////                        singInAdminCliked = {
////                            singInAdmin()
////                        },
////                        singInClientCliked = {
////                            singInClient()
////                        },
////                        signOutClicked = {
////                            singOut()
////                        }
////                    )
////                }
////
////            }
////        }
//    }
//
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
//                    firebaseAutWithGoogle(account.idToken!!)
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
//    private fun firebaseAutWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    //Logado com sucesso
//                    Toast.makeText(this, "Logado com sucesso!", Toast.LENGTH_SHORT).show()
////                    setContent {
////                        LlasPedidosTheme {
////                            val user: FirebaseUser = mAuth.currentUser!!
////                            ProfileScreen(
////                                profileImage = user.photoUrl!!,
////                                name = user.displayName!!,
////                                email = user.email!!,
////                                singInAdminCliked = {
////                                    singInAdmin()
////                                },
////                                singInClientCliked = {
////                                    singInClient()
////                                },
////                                signOutClicked = {
////                                    singOut()
////                                }
////                            )
////                        }
////                    }
//                } else {
//                    //Error ao logar
//                    Toast.makeText(this, "Erro ao logar!", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//
//    fun singIn() {
//        val signInClient = googleSingInClient.signInIntent
//        startActivityForResult(signInClient, RC_SING_IN)
////        RootNavigationGraph(navController = rememberNavController())
//    }
//
//    private fun singInAdmin() {
//        setContent {
//            LlasPedidosTheme {
//                AdminAuth(
//                    disgraca = {
//                        test()
//                    }
//                )
//            }
//        }
//    }
//
//    private fun test() {
//        setContent {
//            LlasPedidosTheme {
////                AdminMainScreen()
//                newItem()
//
//            }
//        }
//    }
//
//    private fun newItem() {
//        setContent {
//            LlasPedidosTheme {
////                CardapioScreen(
////                    clik = {
////                        newItemChama()
////                    }
////                )
//            }
//        }
//    }
//
//    private fun newItemChama() {
//        setContent {
//            LlasPedidosTheme {
//                CreateNewItem()
//            }
//        }
//    }
//
//    private fun singInClient() {
//        setContent {
//            LlasPedidosTheme {
////                ClientMainScreen()
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
//                Toast.makeText(this, "Desconectado com sucesso!", Toast.LENGTH_SHORT).show()
////                setContent {
////                    LlasPedidosTheme {
//////                        GoogleSignInButton {
//////                            singIn()
//////                        }
////                    }
////                }
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Falha ao desconectar!", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LlasPedidosTheme {
////        Column (
////            modifier = Modifier
////                .fillMaxSize()
////                .background(Pink),
////            verticalArrangement = Arrangement.Center,
////            horizontalAlignment = Alignment.CenterHorizontally
////        ){
////            Image(
////                painter = painterResource(id = R.drawable.logo),
////                contentDescription = null,
////                modifier = Modifier.fillMaxWidth()
////            )
////        }
//    }
//}
