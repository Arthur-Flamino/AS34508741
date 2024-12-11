package br.edu.up.as34508741.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.as34508741.dados.Objeto
import br.edu.up.as34508741.dados.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ObjetoViewModel(private val repository :RemoteRepository) : ViewModel() {

    private val _objetos = MutableStateFlow<List<Objeto>>(emptyList())
    val objetos: StateFlow<List<Objeto>> get() = _objetos


    init {
        viewModelScope.launch {
            repository.listarObjeto().collect { objetos ->
                _objetos.value = objetos
            }
        }
    }

    fun gravarObjeto(objeto: Objeto) {
        viewModelScope.launch {
            repository.gravarObjeto(objeto)
        }
    }

    suspend fun buscarObjetoPorId(id: Int): Objeto? {
        return repository.buscarID(id)
    }

    fun deletarObjeto(objeto: Objeto) {
        viewModelScope.launch {
            repository.deletarObjeto(objeto)
        }
    }
}