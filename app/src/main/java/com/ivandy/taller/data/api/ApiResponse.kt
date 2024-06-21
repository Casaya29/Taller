package com.ivandy.taller.data.api

import com.google.gson.annotations.SerializedName
import com.ivandy.taller.utils.Constantes

data class ApiResponseSuccessful(
    @SerializedName(value = Constantes.RESPONSE_SUCCESSFUL)
    val result : String
)

data class ApiResponseError(
    @SerializedName(value = Constantes.RESPONSE_ERROR)
    val message : String
)