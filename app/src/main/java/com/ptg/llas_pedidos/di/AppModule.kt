package com.ptg.llas_pedidos.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ptg.llas_pedidos.core.Constants.BOOKS
import com.ptg.llas_pedidos.data.repository.BooksRepositoryImpl
import com.ptg.llas_pedidos.domain.repository.BooksRepository
import com.ptg.llas_pedidos.domain.use_case.AddBook
import com.ptg.llas_pedidos.domain.use_case.DeleteBook
import com.ptg.llas_pedidos.domain.use_case.GetBooks
import com.ptg.llas_pedidos.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideBooksRef(
        db: FirebaseFirestore
    ) = db.collection(BOOKS)

    @Provides
    fun provideBooksRepository(
        booksRef: CollectionReference
    ): BooksRepository = BooksRepositoryImpl(booksRef)

    @Provides
    fun provideUseCases(
        repo: BooksRepository
    ) = UseCases(
        getBooks = GetBooks(repo),
        addBook = AddBook(repo),
        deleteBook = DeleteBook(repo)
    )
}