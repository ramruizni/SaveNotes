package com.example.savenotes.datasource.converters

import com.example.savenotes.database.NoteDbDto
import com.example.savenotes.domain.models.Note

fun Note.toDbDto(): NoteDbDto {
    return NoteDbDto(
        id = id,
        text = text
    )
}

fun NoteDbDto.toNote(): Note {
    return Note(
        id = id,
        text = text
    )
}
