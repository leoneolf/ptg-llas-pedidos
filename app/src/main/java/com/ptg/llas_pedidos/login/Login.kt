package com.ptg.llas_pedidos.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme

val offset = Offset(5.0f, 10.0f)

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage: () -> Unit,
    onNavToSignUpPage: () -> Unit,
    ) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            fontFamily = FontFamily.Cursive,
            text = "Anota Aí",
            style = TextStyle(
                fontSize = 70.sp,
                shadow = Shadow(
                    color = Color.Black,
                    offset = offset
                )
            ),
            fontWeight = FontWeight.Black,
            color = Color.Cyan
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            fontFamily = FontFamily.Cursive,
            text = "Login",
            style = TextStyle(
                fontSize = 48.sp,
                shadow = Shadow(
                    color = Color.Cyan,
                    offset = offset
                )
            ),
            fontWeight = FontWeight.Black,
            color = Color.Black
        )

        if (isError) {
            Text(
                text = loginUiState?.loginError ?: "Erro desconhecido",
                color = Color.Red,
            )
        }

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                focusedLabelColor = Color.Cyan,
                cursorColor = Color.Cyan
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.userName ?: "",
            onValueChange = { loginViewModel?.onUserNameChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "E-mail")
            },
            isError = isError
        )
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                focusedLabelColor = Color.Cyan,
                cursorColor = Color.Cyan
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.password ?: "",
            onValueChange = { loginViewModel?.onPasswordNameChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "Senha")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                loginViewModel?.loginUser(context)
            },
            shape = RoundedCornerShape(75),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan,
            )
        ) {
            Text(
                fontFamily = FontFamily.Cursive,
                text = "Entrar",
                style = TextStyle(
                    fontSize = 26.sp,
                    shadow = Shadow(
                        color = Color.Cyan,
                        offset = offset
                    )
                ),
                fontWeight = FontWeight.Black,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                fontFamily = FontFamily.Cursive,
                text = "Não tem uma conta?",
                style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(
                        color = Color.Cyan,
                        offset = offset
                    )
                ),
                fontWeight = FontWeight.Black,
                color = Color.Black
            )
//            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = {
                    onNavToSignUpPage.invoke()
                },
                shape = RoundedCornerShape(75)
            ) {
                Text(
                    fontFamily = FontFamily.Cursive,
                    text = "Cadastre-se",
                    style = TextStyle(
                        fontSize = 20.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset
                        )
                    ),
                    fontWeight = FontWeight.Black,
                    color = Color.Cyan
                )
            }
        }

        if (loginUiState?.isLoading == true) {
            CircularProgressIndicator(
                color = Color.Cyan
            )
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser) {
            if (loginViewModel?.hasUser == true) {
                onNavToHomePage.invoke()
            }
        }
    }


}

@Composable
fun SignUpScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage: () -> Unit,
    onNavToLoginPage: () -> Unit,
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            fontFamily = FontFamily.Cursive,
            text = "Cadastro",
            style = TextStyle(
                fontSize = 48.sp,
                shadow = Shadow(
                    color = Color.Cyan,
                    offset = offset
                )
            ),
            fontWeight = FontWeight.Black,
            color = Color.Black
        )

        if (isError) {
            Text(
                text = loginUiState?.signUpError ?: "Erro desconhecido",
                color = Color.Red,
            )
        }

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                focusedLabelColor = Color.Cyan,
                cursorColor = Color.Cyan
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.userNameSignUp ?: "",
            onValueChange = { loginViewModel?.onUserNameChangeSignup(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "E-mail")
            },
            isError = isError
        )
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                focusedLabelColor = Color.Cyan,
                cursorColor = Color.Cyan
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.passwordSignUp ?: "",
            onValueChange = { loginViewModel?.onPasswordChangeSignup(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "Senha")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                focusedLabelColor = Color.Cyan,
                cursorColor = Color.Cyan
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.confirmPasswordSignUp ?: "",
            onValueChange = { loginViewModel?.onConfirmPasswordChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = "Confirmar a senha")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                loginViewModel?.createUser(context)
            },
            shape = RoundedCornerShape(75),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan,
            )
        ) {
            Text(
                fontFamily = FontFamily.Cursive,
                text = "Cadastrar",
                style = TextStyle(
                    fontSize = 26.sp,
                    shadow = Shadow(
                        color = Color.Cyan,
                        offset = offset
                    )
                ),
                fontWeight = FontWeight.Black,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                fontFamily = FontFamily.Cursive,
                text = "Já tem uma conta?",
                style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(
                        color = Color.Cyan,
                        offset = offset
                    )
                ),
                fontWeight = FontWeight.Black,
                color = Color.Black
            )
//            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = {
                    onNavToLoginPage.invoke()
                },
                shape = RoundedCornerShape(75)
            ) {
                Text(
                    fontFamily = FontFamily.Cursive,
                    text = "Entrar",
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset
                        )
                    ),
                    fontWeight = FontWeight.Black,
                    color = Color.Cyan
                )
            }
        }

        if (loginUiState?.isLoading == true) {
            CircularProgressIndicator(
                color = Color.Cyan
            )
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser) {
            if (loginViewModel?.hasUser == true) {
                onNavToHomePage.invoke()
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen() {
    LlasPedidosTheme {
        LoginScreen(onNavToHomePage = { /*TODO*/ }) {

        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PrevSignUpScreen() {
    LlasPedidosTheme {
        SignUpScreen(onNavToHomePage = { /*TODO*/ }) {

        }
    }
}
