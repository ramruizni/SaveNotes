package com.example.savenotes.di

import android.content.Context
import androidx.room.Room
import com.example.savenotes.database.AppDatabase
import com.example.savenotes.database.NoteDao
import com.example.savenotes.database.PokemonDao
import com.example.savenotes.datasource.NoteDataSource
import com.example.savenotes.datasource.NoteDataSourceImpl
import com.example.savenotes.datasource.PokemonDataSource
import com.example.savenotes.datasource.PokemonDataSourceImpl
import com.example.savenotes.remote.PokemonApi
import com.example.savenotes.repository.NoteRepository
import com.example.savenotes.repository.NoteRepositoryImpl
import com.example.savenotes.repository.PokemonRepository
import com.example.savenotes.repository.PokemonRepositoryImpl
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

    @Singleton
    @Provides
    fun providePokemonDao(
        appDatabase: AppDatabase
    ): PokemonDao = appDatabase.pokemonDao()

    @Singleton
    @Provides
    fun providePokemonDataSource(
        dao: PokemonDao,
        api: PokemonApi
    ): PokemonDataSource = PokemonDataSourceImpl(
        dao = dao,
        api = api
    )

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonDataSource: PokemonDataSource
    ): PokemonRepository = PokemonRepositoryImpl(
        pokemonDataSource = pokemonDataSource
    )
}