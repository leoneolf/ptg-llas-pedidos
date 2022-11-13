package com.ptg.llas_pedidos.domain.use_case

import com.ptg.llas_pedidos.domain.repository.BooksRepository

class AddBook(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(
        title: String,
        author: String
    ) = repo.addBookToFirestore(title, author)
}