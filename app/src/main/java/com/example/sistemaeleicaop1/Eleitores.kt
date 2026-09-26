package com.example.sistemaeleicaop1

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sistemaeleicaop1.DadosGlobais // Importe seu DadosGlobais

class Eleitores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleitores)

        // 1. Encontra a "caixa" vazia onde vamos jogar os cartões
        val containerEleitores = findViewById<LinearLayout>(R.id.containerEleitores)

        // 2. Pega a lista de entrevistados salvos em memória
        val lista = DadosGlobais.listaEleitores

        // 3. O LayoutInflater serve para transformar aquele arquivo XML do molde em visual no Kotlin
        val inflater = LayoutInflater.from(this)

        // 4. Se a lista estiver vazia (ninguém foi entrevistado ainda)
        if (lista.isEmpty()) {
            val tvVazio = TextView(this)
            tvVazio.text = "Nenhum eleitor registrado ainda."
            tvVazio.setTextColor(android.graphics.Color.WHITE)
            tvVazio.textSize = 18f
            containerEleitores.addView(tvVazio)
        } else {
            // 5. Para cada eleitor na nossa lista, fazemos isso:
            for (eleitor in lista) {
                // "Copia e cola" o molde visual do item_eleitor
                val cartaoEleitor = inflater.inflate(R.layout.item_eleitor, containerEleitores, false)

                // Mapeia as partes do texto dentro desse cartão específico
                val tvNome = cartaoEleitor.findViewById<TextView>(R.id.tvNomeEleitor)
                val tvCelular = cartaoEleitor.findViewById<TextView>(R.id.tvCelularEleitor)
                val tvDataHora = cartaoEleitor.findViewById<TextView>(R.id.tvDataHoraEleitor)
                val tvLocalizacao = cartaoEleitor.findViewById<TextView>(R.id.tvLocalizacaoEleitor)

                // Preenche os dados reais da pessoa
                tvNome.text = eleitor.nome
                tvCelular.text = eleitor.celular
                tvDataHora.text = eleitor.dataHora
                tvLocalizacao.text = eleitor.localizacao

                // Finalmente, insere esse cartão preenchido na tela (na nossa "caixa" do ScrollView)
                containerEleitores.addView(cartaoEleitor)
            }
        }
    }
}