package com.example.savenotes.datasource.converters

import com.example.savenotes.database.NoteDbDto
import com.example.savenotes.repository.Note

//Estas son funciones de extensión, al declarar 'fun Note.', 'toDbDto' se añade a la clase 'Note',
// y por esta razón podemos llamar a toDbDto como una  instancia de la clase. Además, como la
// función opera dobre una instancia de Note, podemos acceder a los valores públicos de la misma
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


//Beneficios de usar mappers: Tu capa de dominio no necesita saber nada sobre cómo se
// almacenan los datos en la base de datos o cómo se reciben de la red. Solo trabaja con
// objetos Note, y así mismo tu capa de base de datos solo se preocupa por NoteDbDto.
// Puedes diseñar tu modelo de dominio (Note) para que sea exactamente como lo necesitas para
// tu lógica de negocio, sin estar limitado por las estructuras de la base de datos o la red.