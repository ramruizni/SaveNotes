package com.example.savenotes.di

import com.example.savenotes.domain.login.usecases.ValidateEmail
import com.example.savenotes.domain.notes.usecases.GetAllNotes
import com.example.savenotes.domain.notes.usecases.ObserveAllNotes
import com.example.savenotes.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllNotes(
        repository: NoteRepository
    ): GetAllNotes {
        return GetAllNotes(repository)
    }

    @Singleton
    @Provides
    fun provideObserveAllNotes(
        repository: NoteRepository
    ): ObserveAllNotes {
        return ObserveAllNotes(repository)
    }

    @Singleton
    @Provides
    fun provideValidateEmail() : ValidateEmail = ValidateEmail()
}