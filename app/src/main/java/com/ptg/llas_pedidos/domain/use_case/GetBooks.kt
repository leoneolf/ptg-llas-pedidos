package com.ptg.llas_pedidos.domain.use_case

import com.ptg.llas_pedidos.domain.repository.BooksRepository

class GetBooks(
    private val repo: BooksRepository
) {
    operator fun invoke() = repo.getBooksFromFirestore()
}