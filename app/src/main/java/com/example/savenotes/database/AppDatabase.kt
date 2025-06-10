package com.example.savenotes.database

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database marca una clase como una base de datos de Room. Le dice a Room que esta
// clase representa y configura la base de datos SQLite subyacente
// 'entities = [Note::class]' especifica una lista de todas las clases de entidad que
// pertenecen a esta base de datos. Cada clase listada aquí (y anotada con @Entity)
// se convertirá en una tabla en la base de datos.
// 'version = 1' define la versión actual de tu esquema de base de datos, si
// realizáramos cambios en la estructura de tu base de datos, deberíamos incrementar
// este número de versión, para el manejo de las migraciones de la base de datos.
@Database(entities = [NoteDbDto::class], version = 1)
//'class AppDatabase' debe ser abstract porque Room generará la implementación concreta
// de esta clase durante la compilación.
//': RoomDatabase()' indica que AppDatabase hereda de androidx.room.RoomDatabase.
// Esta es una clase base proporcionada por Room que contiene la lógica necesaria
// para gestionar la base de datos.
abstract class AppDatabase: RoomDatabase() {
    //': NoteDao' es el tipo de retorno del metodo. Debe ser el tipo de tu interfaz DAO
    abstract fun noteDao(): NoteDao
    //Room implementará este metodo abstracto por nosotros. Cuando llamemos a
    // myAppDatabaseInstance.noteDao(), Room nos devolverá una instancia completamente
    // funcional de nustro NoteDao, lista para ser usada para interactuar con la tabla notes.
}