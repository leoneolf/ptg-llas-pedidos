package com.ptg.llas_pedidos.presentation.books

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ptg.llas_pedidos.components.TopBar
import com.ptg.llas_pedidos.presentation.books.components.*

@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Books(
                booksContent = { books ->
                    BooksContent(
                        padding = padding,
                        books = books,
                        deleteBook = { bookId ->
                            viewModel.deleteBook(bookId)
                        }
                    )
                    AddBookAlertDialog(
                        openDialog = viewModel.openDialog,
                        closeDialog = {
                            viewModel.closeDialog()
                        },
                        addBook = { title, author ->
                            viewModel.addBook(title, author)
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            AddBookFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )
    AddBook()
    DeleteBook()
}