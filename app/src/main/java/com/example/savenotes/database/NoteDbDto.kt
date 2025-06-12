package com.example.savenotes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDbDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
)