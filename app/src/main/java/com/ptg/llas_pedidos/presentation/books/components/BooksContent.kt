package com.ptg.llas_pedidos.presentation.books.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ptg.llas_pedidos.domain.repository.Books

@Composable
fun BooksContent(
    padding: PaddingValues,
    books: Books,
    deleteBook: (bookId: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = books
        ) { book ->
            BookCard(
                book = book,
                deleteBook = {
                    book.id?.let { bookId ->
                        deleteBook(bookId)
                    }
                }
            )
        }
    }
}