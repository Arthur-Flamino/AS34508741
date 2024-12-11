package br.edu.up.as34508741.dados

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class RemoteRepository() : IRepository {

    var db = FirebaseFirestore.getInstance()
    var objetoColecao = db.collection("objeto")

    override fun listarObjeto(): Flow<List<Objeto>> = callbackFlow {
        val listener = objetoColecao.addSnapshotListener { dados, erros ->
            if (erros != null) {
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null) {
                val objetos = dados.documents.mapNotNull { dado ->
                    dado.toObject(Objeto::class.java)
                }
                trySend(objetos)
            }
        }
        awaitClose { listener.remove() }
    }

    suspend fun getId(): Int {
        val dados = objetoColecao.get().await()

        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarObjeto(objeto: Objeto) {
        val document: DocumentReference

        if (objeto.id == null) {
            objeto.id = getId()
            document = objetoColecao.document(objeto.id.toString())
        } else {
            document = objetoColecao.document(objeto.id.toString())
        }
        document.set(objeto).await()
    }

    override suspend fun buscarID(idx: Int): Objeto? {
        val dados = objetoColecao.document(idx.toString()).get().await()

        return dados.toObject(Objeto::class.java)
    }

    override suspend fun deletarObjeto(objeto: Objeto) {
        if (objeto.id != null) {
            val document = objetoColecao.document(objeto.id.toString())

            document.delete().await()
        }
    }
}