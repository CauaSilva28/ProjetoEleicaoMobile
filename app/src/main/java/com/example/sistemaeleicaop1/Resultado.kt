package com.example.sistemaeleicaop1

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sistemaeleicaop1.DadosGlobais // Importe o seu DadosGlobais

class Resultado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val tvTotalEntrevistados = findViewById<TextView>(R.id.tvTotalEntrevistados)
        val containerResultados = findViewById<LinearLayout>(R.id.containerResultados)

        // 1. Pega a quantidade de pessoas que passaram pelo fluxo completo
        val totalEleitores = DadosGlobais.listaEleitores.size
        tvTotalEntrevistados.text = "Quantidade de pessoas entrevistadas: $totalEleitores"

        // 2. Calcula a soma total de todos os votos armazenados para fazer a porcentagem
        val votos = DadosGlobais.contagemVotos
        var totalVotos = 0
        for (quantidade in votos.values) {
            totalVotos += quantidade
        }

        // 3. Preenche a lista de resultados
        if (totalVotos == 0) {
            // Se ninguém votou ainda
            val tvVazio = TextView(this)
            tvVazio.text = "Nenhum voto foi registrado ainda."
            tvVazio.setTextColor(android.graphics.Color.WHITE)
            tvVazio.textSize = 18f
            containerResultados.addView(tvVazio)
        } else {
            // Se tiver votos, calcula e mostra cada um
            for ((candidato, quantidadeDeVotos) in votos) {

                // Regra de 3 para descobrir a porcentagem (transforma em Float para ter números quebrados)
                val porcentagem = (quantidadeDeVotos.toFloat() / totalVotos.toFloat()) * 100

                // Cria um TextView visual via código
                val tvResultadoCandidato = TextView(this)

                // Formata o texto para ficar ex: "Jason: 40 = 50.0%" (%.1f arredonda para 1 casa decimal)
                val textoFormatado = String.format("%s: %d = %.1f%%", candidato, quantidadeDeVotos, porcentagem)

                tvResultadoCandidato.text = textoFormatado
                tvResultadoCandidato.setTextColor(android.graphics.Color.WHITE)
                tvResultadoCandidato.textSize = 20f
                tvResultadoCandidato.setPadding(0, 0, 0, 24) // Espaçamento de 24 pixels em baixo

                // Joga o texto criado dentro da nossa caixa na tela
                containerResultados.addView(tvResultadoCandidato)
            }
        }
    }
}