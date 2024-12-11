package br.edu.up.as34508741.dados

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Objeto(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nome: String,
    val descricao: String
) {
    constructor() : this(null, "", "")
}