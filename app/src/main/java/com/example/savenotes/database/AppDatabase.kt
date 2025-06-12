package com.example.savenotes.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteDbDto::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}