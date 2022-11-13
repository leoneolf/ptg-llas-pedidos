package com.ptg.llas_pedidos.presentation.books.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ptg.llas_pedidos.components.ProgressBar
import com.ptg.llas_pedidos.core.Utils.Companion.print
import com.ptg.llas_pedidos.domain.model.Response.*
import com.ptg.llas_pedidos.presentation.books.BooksViewModel

@Composable
fun DeleteBook(
    viewModel: BooksViewModel = hiltViewModel()
) {
    when(val deleteBookResponse = viewModel.deleteBookResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> print(deleteBookResponse.e)
    }
}