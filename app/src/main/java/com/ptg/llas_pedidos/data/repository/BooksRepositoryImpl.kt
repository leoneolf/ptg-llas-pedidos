package com.ptg.llas_pedidos.data.repository

import com.google.firebase.firestore.CollectionReference
import com.ptg.llas_pedidos.core.Constants.TITLE
import com.ptg.llas_pedidos.domain.model.Book
import com.ptg.llas_pedidos.domain.model.Response.*
import com.ptg.llas_pedidos.domain.repository.AddBookResponse
import com.ptg.llas_pedidos.domain.repository.BooksRepository
import com.ptg.llas_pedidos.domain.repository.DeleteBookResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepositoryImpl @Inject constructor(
    private val booksRef: CollectionReference
): BooksRepository {
    override fun getBooksFromFirestore() = callbackFlow {
        val snapshotListener = booksRef.orderBy(TITLE).addSnapshotListener { snapshot, e ->
            val booksResponse = if (snapshot != null) {
                val books = snapshot.toObjects(Book::class.java)
                Success(books)
            } else {
                Failure(e)
            }
            trySend(booksResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addBookToFirestore(title: String, author: String): AddBookResponse {
        return try {
            val id = booksRef.document().id
            val book = Book(
                id = id,
                title = title,
                author = author
            )
            booksRef.document(id).set(book).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun deleteBookFromFirestore(bookId: String): DeleteBookResponse {
        return try {
            booksRef.document(bookId).delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}