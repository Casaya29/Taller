package com.ivandy.taller.ui.Navegacion

sealed class Rutasvistas(var route: String){
    object Inicio: Rutasvistas("inicio")
    object Agregar: Rutasvistas("agregar")
    object Editar: Rutasvistas("editar")
}