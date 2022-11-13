package com.ptg.llas_pedidos.domain.repository

import com.ptg.llas_pedidos.domain.model.Book
import com.ptg.llas_pedidos.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Books = List<Book>
typealias BooksResponse = Response<Books>
typealias AddBookResponse = Response<Boolean>
typealias DeleteBookResponse = Response<Boolean>

interface BooksRepository {
    fun getBooksFromFirestore(): Flow<BooksResponse>

    suspend fun addBookToFirestore(title: String, author: String): AddBookResponse

    suspend fun deleteBookFromFirestore(bookId: String): DeleteBookResponse
}