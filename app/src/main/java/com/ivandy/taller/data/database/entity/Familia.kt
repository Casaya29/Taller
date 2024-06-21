package com.ivandy.taller.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Familia")
data class Familia(
    @PrimaryKey
    @ColumnInfo(name = "apellido")
    val apellido: String = "",

    @ColumnInfo("ubicacion")
    val ubicacion: String = "",

    @ColumnInfo("vivienda")
    val vivienda: String = "",

    @ColumnInfo("riesgo")
    val riesgo: String = ""
)
