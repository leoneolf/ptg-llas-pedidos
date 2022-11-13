package com.ptg.llas_pedidos.presentation.books.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ptg.llas_pedidos.components.ProgressBar
import com.ptg.llas_pedidos.core.Utils.Companion.print
import com.ptg.llas_pedidos.domain.model.Response.*
import com.ptg.llas_pedidos.domain.repository.Books
import com.ptg.llas_pedidos.presentation.books.BooksViewModel

@Composable
fun Books(
    viewModel: BooksViewModel = hiltViewModel(),
    booksContent: @Composable (books: Books) -> Unit
) {
    when(val booksResponse = viewModel.booksResponse) {
        is Loading -> ProgressBar()
        is Success -> booksContent(booksResponse.data)
        is Failure -> print(booksResponse.e)
    }
}