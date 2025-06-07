package com.example.savenotes.di

import android.content.Context
import androidx.room.Room
import com.example.savenotes.database.AppDatabase
import com.example.savenotes.database.NoteDao
import com.example.savenotes.datasource.NoteDataSource
import com.example.savenotes.datasource.NoteDataSourceImpl
import com.example.savenotes.repository.NoteRepository
import com.example.savenotes.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "notes-db"
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(
        appDatabase: AppDatabase
    ): NoteDao = appDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteDataSource(
        noteDao: NoteDao
    ): NoteDataSource = NoteDataSourceImpl(
        noteDao = noteDao
    )

    @Singleton
    @Provides
    fun provideNoteRepository(
        noteDataSource: NoteDataSource
    ): NoteRepository = NoteRepositoryImpl(
        noteDataSource = noteDataSource
    )
}