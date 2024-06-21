package com.ivandy.taller.utils

object Constantes{

    //api service
    const val BASE_URL = "http://192.168.1.7:3000"
    const val API_PATH = "/api"

    //Insertar una familia
    const val POST_FAMILIA_PATH = "/postfamilia"
    const val FAMILIA_ID = "_id"
    const val FAMILIA_APELLIDO = "apellido"
    const val FAMILIA_UBICACION = "ubicacion"
    const val FAMILIA_VIVIENDA = "vivienda"
    const val FAMILIA_RIESGO = "riesgo"



    //Seleccionar todas las familia
    const val GET_ALL_FAMILIA_PATH = "/getallfamilia"

    //Seleccionar una familia
    const val GET_FAMILIA_PATH = "/getfamilia"

    //Actualizar una familia
    const val PATCH_FAMILIA_PATH = "/updatefamilia"

    //Eliminar una familia
    const val DELETE_FAMILIA_PATH = "/deletefamilia"

    // Api response
    const val RESPONSE_SUCCESSFUL = "result"
    const val RESPONSE_ERROR = "message"
}