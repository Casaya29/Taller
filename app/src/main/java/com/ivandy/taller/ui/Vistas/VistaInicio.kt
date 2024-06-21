package com.ivandy.taller.ui.Vistas

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ivandy.taller.MainViewModel
import com.ivandy.taller.UiState
import com.ivandy.taller.data.familiaList
import com.ivandy.taller.ui.Componentes.ListItem
import com.ivandy.taller.ui.Componentes.TopBar
import com.ivandy.taller.ui.Navegacion.Rutasvistas

@Composable
fun VistaInicio(
    viewModel: MainViewModel,
    navController: NavController
    //MainViewModel(), rememberNavController())
) {
    LaunchedEffect(Unit) {
        viewModel.getAllFamilias()
    }
    val VistaInicioState = viewModel.uiState.collectAsState()

    if (VistaInicioState.value is UiState.Error){
        val message = (VistaInicioState.value as UiState.Error).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
    }

    Scaffold(

        topBar = {
            TopBar(
                "Familias (apellido - ubicacion)",
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Rutasvistas.Agregar.route) }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")

            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(familiaList){ familia ->

                ListItem(
                    familia,
                    onItemClick = { selectedCourse ->
                        navController.navigate(route = "${Rutasvistas.Editar.route}/${familia.id}")
                    }
                )
                Divider(thickness = 1.dp, color = Color.LightGray)
            }

        }
        }


}
@Preview
@Composable
fun PreviewVistaInicio()
{
    VistaInicio(MainViewModel(), rememberNavController())
}