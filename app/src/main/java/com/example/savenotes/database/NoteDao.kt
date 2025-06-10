package com.example.savenotes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// las DB suelen tener estas operaciones: CRUD
// Create, Read, Update, Delete

//Las DAO (Data Access Object) son el componente principal a través del cual
// interactuamos con los datos almacenados en tu base de datos. Cada DAO incluye
// métodos que ofrecen acceso abstracto a la base de datos de la app.
// La anotación clave @Dao le dice a Room que esta interfaz es un Data Access Object
@Dao
interface NoteDao {

    //Se define un metodo para obtener todas las notas
    //@Query es una anotación se usa para definir métodos que realizan consultas de
    // lectura en la base de datos usando sentencias SQL
    //"SELECT * FROM notes" se ejecutará cuando se llame al metodo 'getAll()'
    //'SELECT *' significa "selecciona todas las columnas" y 'FROM notes' especifica
    // que la selección se hará de la tabla llamada "notes"
    @Query("SELECT * FROM notes")
    // al ser una función suspend Room se encargará de ejecutar la consulta en un hilo
    // de fondo y suspenderá la coroutine que la llama hasta que los resultados estén
    // listos.
    suspend fun getAll(): List<Note>  // Especifica el tipo de retorno. Room mapeará
    // automáticamente las filas recuperadas de la tabla "notes" a una lista de objetos Note

    @Query("SELECT * FROM notes")
    fun observeAll(): Flow<List<Note>>

    //se define un metodo para insertar una nota
    //Room generará el código SQL necesario para insertar la entidad o entidades
    // pasadas como parámetros en la tabla correspondiente.
    @Insert
    // El metodo 'insert' toma un solo objeto Note como parámetro, que es la nota que
    // se desea insertar en la base de datos.
    suspend fun insert(note: Note)

    //Define un metodo para eliminar una nota
    //Room generará el código para eliminar la fila o filas correspondientes a las
    // entidades pasadas. La eliminación se basa típicamente en la clave primaria
    // de la entidad.
    @Delete
    //El metodo toma un objeto Note como parámetro. Room usará la clave primaria (id)
    // del objeto note proporcionado para encontrar y eliminar la fila correspondiente
    // en la tabla "notes".
    suspend fun delete(note: Note)
}