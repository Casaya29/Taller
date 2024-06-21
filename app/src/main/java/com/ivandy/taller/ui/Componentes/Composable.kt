package com.ivandy.taller.ui.Componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.widget.ContentLoadingProgressBar
import com.ivandy.taller.data.api.FamiliaApi
import com.ivandy.taller.data.database.dao.DaoFamilia
import com.ivandy.taller.data.database.entity.Familia

@Composable
fun ListItem(
    familia: FamiliaApi,
    onItemClick: (FamiliaApi) -> Unit = {}
){
     TextButton(
         modifier = Modifier
             .padding(8.dp)
             .fillMaxWidth(),
         onClick = { onItemClick(familia)},
         shape = CutCornerShape(0f)
     ) {
         Text(text = "${familia.apellido} - ${familia.ubicacion}")
     }
}

@Composable
fun LoadingProgressDialog(){
    Dialog(
        onDismissRequest = { },
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(Color.Transparent)
        ){
            CircularProgressIndicator()
        }
    }
}