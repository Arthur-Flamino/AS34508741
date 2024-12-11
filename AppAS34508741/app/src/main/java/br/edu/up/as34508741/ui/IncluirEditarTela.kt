package br.edu.up.as34508741.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.as34508741.dados.Objeto
import kotlinx.coroutines.launch

@Composable
fun IncluirEditarObjetosTela(
    objetoId: Int? = null,
    viewModel: ObjetoViewModel,
    navController: NavController
) {

    var coroutineScope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var objeto: Objeto? by remember { mutableStateOf(null) }

    var label = if (objetoId == null) "Novo Objeto" else "Editar"

    LaunchedEffect(objetoId) {
        coroutineScope.launch {
            if (objetoId != null) {
                objeto = viewModel.buscarObjetoPorId(objetoId)
                objeto?.let {
                    nome = it.nome
                    descricao = it.descricao
                }
            }
        }
    }

    Column(
        modifier = Modifier.padding(
            top = 90.dp,
            start = 30.dp,
            end = 30.dp,
            bottom = 30.dp
        )
    ) {
        Text(
            text = label,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            coroutineScope.launch {
                val objetoSalvar = Objeto(
                    id = objetoId,
                    nome = nome,
                    descricao = descricao
                )
                viewModel.gravarObjeto(objetoSalvar)
                navController.popBackStack()

            }
        }) {
            Text(text = "Salvar", fontSize = 30.sp)
        }
    }

}