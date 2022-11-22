package com.ptg.llas_pedidos.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.ptg.llas_pedidos.Utils
import com.ptg.llas_pedidos.models.Notes
import com.ptg.llas_pedidos.repository.Resources
import com.ptg.llas_pedidos.ui.theme.LlasPedidosTheme
import com.ptg.llas_pedidos.ui.theme.BgColor
import java.text.SimpleDateFormat
import java.util.*


@OptIn(androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun Home(
    homeViewModel: HomeViewModel?,
    onNoteClick: (id: String) -> Unit,
    navToDetailPage: () -> Unit,
    navToLoginPage: () -> Unit,
) {
    val homeUiState = homeViewModel?.homeUiState ?: HomeUiState()

    var openDialog by remember {
        mutableStateOf(false)
    }
    var selectedNote: Notes? by remember {
        mutableStateOf(null)
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit){
        homeViewModel?.loadNotes()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = BgColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navToDetailPage.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = Color.Cyan,
                navigationIcon = {},
                actions = {
                    IconButton(onClick = {
                        homeViewModel?.signOut()
                        navToLoginPage.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = null,
                        )
                    }
                },
                title = {
                    Text(text = "Anota aí")
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (homeUiState.notesList) {
                is Resources.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }

                is Resources.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(16.dp),
                    ) {
                        items(
                            homeUiState.notesList.data ?: emptyList()
                        ) { note ->
                            NoteItem(
                                notes = note,
                                onLongClick = {
                                    openDialog = true
                                    selectedNote = note
                                },
                            ) {
                                onNoteClick.invoke(note.documentId)
                            }
                        }
                    }

                    AnimatedVisibility(visible = openDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                openDialog = false
                            },
                            title = { Text(text = "Deseja realmente apagar?") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        selectedNote?.documentId?.let {
                                            homeViewModel?.deleteNote(it)
                                        }
                                        openDialog = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Red
                                    ),
                                    shape = RoundedCornerShape(75)
                                ) {
                                    Text(text = "Apagar")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = {
                                        openDialog = false
                                    },
                                    shape = RoundedCornerShape(75)
                                ) {
                                    Text(text = "Cancelar")
                                }
                            }
                        )
                    }
                }
                else -> {
                    Text(
                        text = homeUiState
                            .notesList.throwable?.localizedMessage ?: "Erro desconhecido",
                        color = Color.Red
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = homeViewModel?.hasUser){
        if (homeViewModel?.hasUser == false){
            navToLoginPage.invoke()
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    notes: Notes,
    onLongClick: () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .combinedClickable(
                onLongClick = { onLongClick.invoke() },
                onClick = { onClick.invoke() }
            )
            .padding(8.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Black
        ),
        elevation = 10.dp,
        backgroundColor = Utils.colors[notes.colorIndex]
    ) {
        Column {
            Text(
                text = notes.title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text(
                    text = notes.description,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp),
                    maxLines = 4
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text(
                    text = formatDate(notes.timestamp),
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.End),
                    maxLines = 4
                )
            }
        }
    }
}


private fun formatDate(timestamp: Timestamp): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault())
    return sdf.format(timestamp.toDate())
}


@Preview
@Composable
fun PrevHomeScreen() {
    LlasPedidosTheme {
        Home(
            homeViewModel = null,
            onNoteClick = {},
            navToDetailPage = { /*TODO*/ }
        ) {

        }
    }
}