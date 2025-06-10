package com.example.savenotes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity representa una tabla en la base de datos. De forma predeterminada, Room usa
// el nombre de la clase como el nombre de la tabla de la base de datos, sin embargo
// en este caso, 'ableName = "notes"' es un parámetro opcional de la anotación en donde
// se especifica el nombre "notes"
@Entity(tableName = "notes")
//Cada instancia de esta clase representará una fila individual de la tabla "notes"
data class NoteDbDto(
    //@PrimaryKey, que marca la propiedad que sigue (id), se encarga de identificar de
    //manera única cada fila en la tabla de base de datos correspondiente.
    // 'autoGenerate = true' es un parámetro opcional, y al ponerlo en true le estamos
    // indicando a SQLite que genere automáticamente un valor único para esta columna cada
    // vez que se inserta una nueva fila
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    //Los parámetros definidos en el constructor primario de nuestra data class, se
    // convertirán en las columnas de la tabla, es decir que esta tabla tendrá columnas
    // para el ID y para el Texto. Como ya vimos, 'id' está marcado por @PrimaryKey para
    //identificar cada fila, y al definir 'id = 0', Room interpretará esto como una señal
    // para autogenerar el ID.
    val text: String,
)