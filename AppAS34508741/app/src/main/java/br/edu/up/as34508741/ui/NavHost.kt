package br.edu.up.as34508741.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ObjetoNavHost(viewModel: ObjetoViewModel) {

    val navControle = rememberNavController()

    NavHost(navController = navControle, startDestination = "listarObjetos") {

        composable("listarObjetos") { ListarObjetos(viewModel, navControle) }
        composable("AddObjeto") {
            IncluirEditarObjetosTela(viewModel = viewModel, navController = navControle)
        }
        composable("EditarObjeto/{objetoID}") { navBackStackEntry ->
            val objetoId = navBackStackEntry.arguments?.getString("objetoID")?.toInt()
            objetoId?.let { IncluirEditarObjetosTela(it, viewModel, navControle) }
        }

    }
}