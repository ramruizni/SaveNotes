package com.example.savenotes.datasource

import android.content.Context
import androidx.room.Room
import com.example.savenotes.database.AppDatabase
import com.example.savenotes.database.Note
import com.example.savenotes.database.NoteDao

class NoteDataSourceImpl(
    //El Context de Android es necesario para que Room pueda acceder al sistema de
    // archivos de la aplicación y crear o abrir el archivo de la base de datos.
    context: Context,
) : NoteDataSource {

    private var noteDao : NoteDao? = null

    init {
        // Se utiliza el constructor builder de Room para configurar y crear la
        // instancia de la base de datos
        val db = Room.databaseBuilder(
            // se le tiene que pasar context para Room determine dónde almacenar el
            //archivo de la base de datos
            context,
            // 'AppDatabase::class.java' como segundo argumento, especifica la clase
            // que define la base de datos Room. '::class.java' es la sintaxis de
            // Kotlin para obtener la representación de clase Java de una clase Kotlin,
            // que es lo que espera Room.
            AppDatabase::class.java, "notes-db"
            // "note-db" es el nombre del archivo que se utilizará para almacenar la
            // base de datos SQLite en el dispositivo. Si el archivo no existe, Room
            // lo creará. Si ya existe, Room lo abrirá.
        ).build()
        // '.build()' finaliza la configuración y crea la instancia real de la base
        // de datos

        //Aquí se accede a los DAOs de la base de datos a través de su instancia
        noteDao = db.noteDao()
    }

    override suspend fun getAll(): List<Note> {
        return noteDao?.getAll().orEmpty()
    }

    override suspend fun insert(note: Note) {
        noteDao?.insert(note)
    }

    override suspend fun delete(note: Note) {
        noteDao?.delete(note)
    }

}