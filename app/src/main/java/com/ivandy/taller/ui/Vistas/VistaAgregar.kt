package com.ivandy.taller.ui.Vistas

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ivandy.taller.MainViewModel
import com.ivandy.taller.UiState
import com.ivandy.taller.data.api.FamiliaApi
import com.ivandy.taller.data.database.entity.Familia
import com.ivandy.taller.ui.Componentes.LoadingProgressDialog
import com.ivandy.taller.ui.Componentes.TopBar


@Composable
fun VistaAgregar(
    viewModel : MainViewModel,
    navController: NavController
) {
    // Estado que almacena la informacion de la familia
    var familiaData by remember { mutableStateOf(FamiliaApi()) }

    // Controller que gestiona el teclado
    val keyBoardController = LocalSoftwareKeyboardController.current

    // Estado para controlar el estado de la interfaz desde viewModel
    val VistaAgregarState =viewModel.uiState.collectAsState()


    if (VistaAgregarState.value is UiState.Error){
        val message = (VistaAgregarState.value as UiState.Error).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
    }
    if (VistaAgregarState.value is UiState.Success){
        val message = (VistaAgregarState.value as UiState.Success).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
        navController.popBackStack()
    }

    Scaffold(
        topBar ={
            TopBar(
                "Agregar Familia",
                navController = navController,
                onSaveEvent = {
                    // Guardar la informacion de la familia
                    keyBoardController?.hide()
                    viewModel.insertFamilia(familiaData)
                }
            )
        },
    ){paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value =  familiaData.apellido ,
                onValueChange = { updatedString -> familiaData = familiaData.copy(apellido = updatedString) },
                label = { Text(text = "Apellido")},
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value =  familiaData.ubicacion ,
                onValueChange = { updatedString -> familiaData = familiaData.copy(ubicacion = updatedString) },
                label = { Text(text = "Ubicacion")},
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value =  familiaData.vivienda ,
                onValueChange = { updatedString -> familiaData = familiaData.copy(vivienda = updatedString) },
                label = { Text(text = "Vivienda")},
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value =  familiaData.riesgo ,
                onValueChange = { updatedString -> familiaData = familiaData.copy(riesgo = updatedString) },
                label = { Text(text = "Riesgo")},
            )
        }
    }
}

