package com.ivandy.taller.data.api

import com.ivandy.taller.utils.Constantes
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @Headers(value=["Content-Type: application/json"])
    @POST(value = Constantes.API_PATH + Constantes.POST_FAMILIA_PATH)
     suspend fun insertFamilia(@Body familia: FamiliaApi) : ApiResponseSuccessful

        //Seleccionar todas las familias
        @Headers(value=["Content-Type: application/json"])
        @GET(value = Constantes.API_PATH + Constantes.GET_ALL_FAMILIA_PATH)
        suspend fun getAllFamilia() : List<FamiliaApi>

        //Selecione una familia
        @Headers(value=["Content-Type: application/json"])
        @GET(value = Constantes.API_PATH + Constantes.GET_FAMILIA_PATH)
        suspend fun getFamilia(@Query("id") id : String) : FamiliaApi
        //Actualizar una familia
        @Headers(value=["Content-Type: application/json"])
        @PATCH(value = Constantes.API_PATH + Constantes.PATCH_FAMILIA_PATH)
        suspend fun updateFamilia(@Query("id") id:String, @Body familia: FamiliaApi) : ApiResponseSuccessful

        //Eliminar una familia
        @Headers(value=["Content-Type: application/json"])
        @DELETE(value = Constantes.API_PATH + Constantes.DELETE_FAMILIA_PATH)
        suspend fun deleteFamilia(@Query("id") id:String) : ApiResponseSuccessful
}