package com.ivandy.taller.ui.Vistas

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ivandy.taller.MainViewModel
import com.ivandy.taller.UiState
import com.ivandy.taller.data.api.FamiliaApi
import com.ivandy.taller.data.database.entity.Familia
import com.ivandy.taller.ui.Componentes.TopBar
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VistaEditar(
    viewModel : MainViewModel,
    navController: NavController,
    familiaApellido : String?
) {
    LaunchedEffect(Unit) {
        if(familiaApellido != null){
            viewModel.getFamilia(familiaApellido)
        }
    }

    // Estado que almacena la informacion de la familia
    var familiaData by remember { mutableStateOf(FamiliaApi()) }


    //Estado que cambia el comportamiento de los textField (Los habilita para cambios)
    var isFormEnabled by remember { mutableStateOf(false) }

    //Estado que gestiona cuando la ventana emergente de eliminar es visible
    var isDeleteDialogOpen by  remember {
        mutableStateOf(false)
    }

    //Controller que gestiona el teclado
    val keyBoardController = LocalSoftwareKeyboardController.current

    //Obteniendo informacion del curso desde viewModel
    val fetchedFamilia by viewModel.familiaData
    familiaData = fetchedFamilia

    //Estado para controlar el estado de la interfaz desde viewModel
    val VistaEditarState = viewModel.uiState.collectAsState()



    if (VistaEditarState.value is UiState.Error){
        val message = (VistaEditarState.value as UiState.Error).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
    }
    if (VistaEditarState.value is UiState.Success){
        val message = (VistaEditarState.value as UiState.Success).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
        navController.popBackStack()
    }

    Scaffold(

        topBar = {
            TopBar(
                "Editar Familia",
                navController = navController,
                onSaveEvent = {
                    keyBoardController?.hide()
                    viewModel.updateFamilia(familiaData)
                },
                onDeleteEvent = {
                    isDeleteDialogOpen = true
                }

            )
        },

        ) {
            paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentSize(),

                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(0.7f),
                    value = familiaData.apellido,
                    onValueChange = { updatedString -> familiaData = familiaData.copy(apellido = updatedString) },
                    label = { Text(text = "Apellido")},
                    enabled = isFormEnabled,
                )

                Button(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(12.dp),
                    onClick = { isFormEnabled = true }
                ) {
                    Text(text = "Editar")
                }
            }

            Divider(thickness = 1.dp, color = Color.Gray)


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value =  familiaData.ubicacion ,
                onValueChange = {updatedString -> familiaData = familiaData.copy(ubicacion = updatedString) },
                label = { Text(text = "Ubicacion")},
                enabled = isFormEnabled,
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value =  familiaData.vivienda ,
                onValueChange = {updatedString -> familiaData = familiaData.copy(vivienda = updatedString)},
                label = { Text(text = "Vivienda")},
                singleLine = false,
                enabled = isFormEnabled,
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value =  familiaData.riesgo ,
                onValueChange = {updatedString -> familiaData = familiaData.copy(riesgo = updatedString)},
                label = { Text(text = "Riesgo")},
                enabled = isFormEnabled,
            )
        }
    }

    if (isDeleteDialogOpen){

        AlertDialog(
            onDismissRequest = { isDeleteDialogOpen = false },
            confirmButton = {
                Button(
                    onClick = {
                        familiaData.id?.let{valiId ->
                            viewModel.deleteFamilia(valiId)
                        }
                        isDeleteDialogOpen = false
                    }
                ) {
                    Text(text = "Eliminar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { isDeleteDialogOpen = false }
                ) {
                    Text(text = "Cancelar")
                }
            },
            text = {
                Text(text = "Desea eliminar esta familiar?")
            }
        )
    }

}
