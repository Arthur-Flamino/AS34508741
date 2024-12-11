package br.edu.up.as34508741

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.up.as34508741.dados.RemoteRepository
import br.edu.up.as34508741.ui.ObjetoNavHost
import br.edu.up.as34508741.ui.ObjetoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val remoto = RemoteRepository()
        val viewModel = ObjetoViewModel(remoto)

        setContent {
            ObjetoNavHost(viewModel = viewModel)
        }
    }
}
