package com.example.savenotes.di

import com.example.savenotes.domain.login.usecases.ValidateEmail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {


    @Singleton
    @Provides
    fun provideValidateEmail() : ValidateEmail = ValidateEmail()
}