package com.ptg.llas_pedidos.domain.use_case

import com.ptg.llas_pedidos.domain.repository.BooksRepository

class DeleteBook(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(bookId: String) = repo.deleteBookFromFirestore(bookId)
}