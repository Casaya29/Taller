package com.ivandy.taller

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivandy.taller.data.api.ApiClient
import com.ivandy.taller.data.api.FamiliaApi
import com.ivandy.taller.data.database.MiAplicacion
import com.ivandy.taller.data.database.entity.Familia
import com.ivandy.taller.data.familiaList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    //Estados que gestionan el estado de la interfaz
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState

    //Estados que gestionan la informacion de editScreen
    private val _familiaData = mutableStateOf(FamiliaApi())
    val familiaData: State<FamiliaApi> = _familiaData

    private val db = MiAplicacion.database
    private val api = ApiClient.apiService

    //insertar nueva familia

    fun insertFamilia(familia: FamiliaApi){
        viewModelScope.launch (Dispatchers.IO){
            try{
                _uiState.value = UiState.Loading
                val response = api.insertFamilia(familia)
                Log.i("MainViewModel", response.toString())
                _uiState.value = UiState.Success("Familia almacenada correctamente")
            }
            catch (e: Exception){
                when(e){
                    is HttpException -> {
                        Log.i("MainViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                        _uiState.value = UiState.Error( "Error. Contacte con el servicio de soporte")
                    }
                }
            }
        }
    }

    /*fun insertFamilia(familia: Familia) {
        Log.i("MainViewModel", "Ejecutando funcion en viewModel")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Insertar una familia
                db.Daofamilia().insertFamilia(familia)

                _uiState.value = UiState.Success("Familia agregada correctamente!")

            } catch (e: Exception) {
                when (e) {
                    is SQLiteConstraintException -> {
                        Log.i("MainViewModel", "Error: Apellido duplicado")
                        _uiState.value = UiState.Error("Error: Apellido duplicado")
                    }

                    else -> {
                        Log.i("MainViewModel", e.toString())
                        _uiState.value =
                            UiState.Error("Error al intentar acceder a la base de datos")
                    }
                }

            }
        }
    }*/
    //Seleccionar todas las familias

    fun getAllFamilias(){
        viewModelScope.launch (Dispatchers.IO){
            try{
                _uiState.value = UiState.Loading

                val apifamilia = api.getAllFamilia()
                familiaList.clear()
                for(familia in apifamilia){
                    familiaList.add(familia)
                }
                _uiState.value = UiState.Ready
            }
            catch (e: Exception){
                when(e){
                    is HttpException -> {
                        Log.i("MainViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                        _uiState.value = UiState.Error( "Error. Contacte con el servicio de soporte")
                    }
                }
            }
        }
    }
   /* fun getAllFamilias(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val familias = db.Daofamilia().getAllFamilias()
                familiaList.clear()
                for(familia in familias){
                    familiaList.add(familia)
                }
            } catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }
    }*/

    //Seleccionar una familia por apellido
    fun getFamilia(id :String){
        viewModelScope.launch (Dispatchers.IO){
            try{
                _uiState.value = UiState.Loading
               _familiaData.value = api.getFamilia(id)
                _uiState.value = UiState.Ready
            }
            catch (e: Exception){
                when(e){
                    is HttpException -> {
                        Log.i("MainViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                        _uiState.value = UiState.Error( "Error. Contacte con el servicio de soporte")
                    }
                }
            }
        }
    }

  /*  fun getFamilia(apellido : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val familia = db.Daofamilia().getFamilia(apellido)
                _familiaData.value = familia
            } catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }
    }*/

    //Actualizar una familia
    fun updateFamilia(familia: FamiliaApi){
        viewModelScope.launch(Dispatchers.IO){
            try{
                _uiState.value = UiState.Loading
                val response = familia.id?.let {valiId ->
                    api.updateFamilia(valiId, familia)
                }
                Log.i("MainViewModel", response.toString())
                _uiState.value = UiState.Success("Familia actualizada correctamente")
            }
            catch (e: Exception){
                when(e){
                    is HttpException -> {
                        Log.i("MainViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                        _uiState.value = UiState.Error( "Error. Contacte con el servicio de soporte")
                    }
                }
            }
        }
    }

   /* fun updateFamilia(familia: Familia){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                db.Daofamilia().updateFamilia(familia)
                _uiState.value = UiState.Success("Familia actualizada correctamente!")
            } catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }
    }*/

    //Eliminar una familia
       fun deleteFamilia(id: String){
        viewModelScope.launch(Dispatchers.IO){
            try{
                _uiState.value = UiState.Loading
                val response = api.deleteFamilia(id)
                Log.i("MainViewModel", response.toString())
                _uiState.value = UiState.Success("Familia eliminada correctamente")
            }
            catch (e: Exception){
                when(e){
                    is HttpException -> {
                        Log.i("MainViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                        _uiState.value = UiState.Error( "Error. Contacte con el servicio de soporte")
                    }
                }
            }
        }
    }

   /* fun deleteFamilia(familia: Familia){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                db.Daofamilia().deleteFamilia(familia)
                _uiState.value = UiState.Success("Familia eliminada correctamente!")
            } catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }
    }*/

    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }
}

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success(val msg: String) : UiState()
    data class Error(val msg: String) : UiState()
}
