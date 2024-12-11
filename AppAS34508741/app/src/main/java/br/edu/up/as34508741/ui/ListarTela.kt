package br.edu.up.as34508741.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun ListarObjetos(viewModel: ObjetoViewModel, navController: NavController) {

    var coroutineScope = rememberCoroutineScope()


    val objetos by viewModel.objetos.collectAsState()



    Column(
        modifier = Modifier.padding(
            top = 90.dp,
            start = 30.dp,
            end = 30.dp,
            bottom = 30.dp
        )
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Lista de Objetos",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(10.dp))

        for (objeto in objetos) {
            Row {
                Text(
                    text = objeto.nome,
                    fontSize = 30.sp
                )

                Button(onClick = {
                    //Navegação editar
                    navController.navigate("EditarObjeto/${objeto.id}")
                }) {
                    Text(text = "Editar", fontSize = 25.sp)
                }


                Button(onClick = {
                    coroutineScope.launch {
                        viewModel.deletarObjeto(objeto)
                    }
                }) {
                    Text(text = "Excluir", fontSize = 25.sp)
                }
            }

        }

        Button(onClick = {
            navController.navigate("AddObjeto")
        }) {
            Text(text = "Novo Objeto")
        }
    }
}