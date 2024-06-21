package com.ivandy.taller.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivandy.taller.data.database.dao.DaoFamilia
import com.ivandy.taller.data.database.entity.Familia


@Database(
    entities = [Familia::class],
    version = 1
)
abstract class FamiliaDatabase : RoomDatabase(){
    abstract fun Daofamilia(): DaoFamilia

}