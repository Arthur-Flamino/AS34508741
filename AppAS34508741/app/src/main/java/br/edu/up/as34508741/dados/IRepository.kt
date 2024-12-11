package br.edu.up.as34508741.dados

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun listarObjeto(): Flow<List<Objeto>>
    suspend fun gravarObjeto(objeto: Objeto)
    suspend fun buscarID(idx: Int): Objeto?
    suspend fun deletarObjeto(objeto: Objeto)
}