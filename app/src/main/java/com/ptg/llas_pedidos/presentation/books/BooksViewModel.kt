package com.ptg.llas_pedidos.presentation.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptg.llas_pedidos.domain.model.Response.*
import com.ptg.llas_pedidos.domain.repository.AddBookResponse
import com.ptg.llas_pedidos.domain.repository.BooksResponse
import com.ptg.llas_pedidos.domain.repository.DeleteBookResponse
import com.ptg.llas_pedidos.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    var booksResponse by mutableStateOf<BooksResponse>(Loading)
        private set
    var addBookResponse by mutableStateOf<AddBookResponse>(Success(false))
        private set
    var deleteBookResponse by mutableStateOf<DeleteBookResponse>(Success(false))
        private set
    var openDialog by mutableStateOf(false)
        private set

    init {
        getBooks()
    }

    private fun getBooks() = viewModelScope.launch {
        useCases.getBooks().collect { response ->
            booksResponse = response
        }
    }

    fun addBook(title: String, author: String) = viewModelScope.launch {
        addBookResponse = Loading
        addBookResponse = useCases.addBook(title, author)
    }

    fun deleteBook(bookId: String) = viewModelScope.launch {
        deleteBookResponse = Loading
        deleteBookResponse = useCases.deleteBook(bookId)
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}