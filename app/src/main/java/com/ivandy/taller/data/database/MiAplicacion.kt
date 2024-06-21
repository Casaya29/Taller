package com.ivandy.taller.data.database

import android.app.Application
import androidx.room.Room


class MiAplicacion : Application(){
    companion object{
        lateinit var database : FamiliaDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            FamiliaDatabase::class.java,
            "FamiliaDatabase"

        ).fallbackToDestructiveMigration().build()
    }
}