package com.ivandy.taller.ui.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ivandy.taller.ui.Vistas.VistaAgregar
import com.ivandy.taller.ui.Vistas.VistaEditar
import com.ivandy.taller.ui.Vistas.VistaInicio
import com.ivandy.taller.MainViewModel

@Composable
fun Navegacion(
    viewModel: MainViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutasvistas.Inicio.route
    ){
        composable(route=Rutasvistas.Inicio.route){
            VistaInicio(viewModel, navController)
        }

        composable(route=Rutasvistas.Agregar.route){
            VistaAgregar(viewModel, navController)
        }

        composable(
            route="${Rutasvistas.Editar.route}/{apellido}",
            arguments = listOf(
                navArgument("apellido"){
                    type= NavType.StringType
                }
            )
        ){ backStackEntry ->
            VistaEditar(viewModel, navController, backStackEntry.arguments?.getString("apellido") )
        }
    }

}