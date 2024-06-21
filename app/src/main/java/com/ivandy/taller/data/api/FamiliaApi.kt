package com.ivandy.taller.data.api

import com.google.gson.annotations.SerializedName
import com.ivandy.taller.utils.Constantes


data class FamiliaApi (
    @SerializedName(value = Constantes.FAMILIA_ID)
    val id: String? = null,

    @SerializedName(value = Constantes.FAMILIA_APELLIDO)
    val apellido: String = "",

    @SerializedName(value = Constantes.FAMILIA_UBICACION)
    val ubicacion: String = "",

    @SerializedName(value = Constantes.FAMILIA_VIVIENDA)
    val vivienda: String = "",

    @SerializedName(value = Constantes.FAMILIA_RIESGO)
    val riesgo: String = "",

)