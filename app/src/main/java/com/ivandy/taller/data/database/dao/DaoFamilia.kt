package com.ivandy.taller.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ivandy.taller.data.database.entity.Familia

@Dao
interface DaoFamilia {
    //Seleccionar todas las familias
    @Query("SELECT * FROM Familia")
    suspend fun getAllFamilias(): MutableList<Familia>

    //Seleccionar una familia específica
    @Query("SELECT * FROM Familia WHERE apellido=:apellido")
    suspend fun getFamilia(apellido: String): Familia

    //Insertar familia
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFamilia(familia: Familia)

    //Actualizar familia
    @Update
    suspend fun updateFamilia(familia: Familia)

    //Eliminar familia
    @Delete
    suspend fun deleteFamilia(familia: Familia)
}