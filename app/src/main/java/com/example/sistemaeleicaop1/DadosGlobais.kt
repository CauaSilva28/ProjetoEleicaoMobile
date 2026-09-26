package com.example.sistemaeleicaop1

// Estrutura para guardar os dados de cada pessoa entrevistada
data class Eleitor(
    val nome: String,
    val celular: String,
    val dataHora: String,
    val localizacao: String
)

object DadosGlobais {
    // Mapa para a tela de "Resultado" (Gráfico/Contagem)
    val contagemVotos = mutableMapOf<String, Int>(
        "Jason" to 0,
        "Thanos" to 0,
        "Darth Vader" to 0,
        "Coringa" to 0,
        "Godzilla" to 0,
        "Branco" to 0,
        "Nulo" to 0,
        "Não Sei" to 0
    )

    // Lista para a tela de "Eleitores"
    val listaEleitores = mutableListOf<Eleitor>()

    fun adicionarVoto(candidato: String) {
        val votosAtuais = contagemVotos[candidato] ?: 0
        contagemVotos[candidato] = votosAtuais + 1
    }

    fun adicionarEleitor(eleitor: Eleitor) {
        listaEleitores.add(eleitor)
    }
}