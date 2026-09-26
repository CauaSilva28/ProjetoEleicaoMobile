package com.seudominio.sistemaeleicaop1 // Substitua pelo seu pacote

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sistemaeleicaop1.R

class Problemas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problemas)

        val cbSaude = findViewById<CheckBox>(R.id.checkSaude)
        val cbEducacao = findViewById<CheckBox>(R.id.checkEducacao)
        val cbTransporte = findViewById<CheckBox>(R.id.checkTransporte)
        val cbViolencia = findViewById<CheckBox>(R.id.checkViolencia)
        val cbMeioAmbiente = findViewById<CheckBox>(R.id.checkMeioAmbiente)
        val cbTecnologia = findViewById<CheckBox>(R.id.checkTecnologia)
        val cbArborizacao = findViewById<CheckBox>(R.id.checkArborizacao)
        val cbEconomia = findViewById<CheckBox>(R.id.checkEconomia)
        val cbNatalidade = findViewById<CheckBox>(R.id.checkNatalidade)

        val btnConfirmar = findViewById<Button>(R.id.buttonConfirmarProblemas)

        val todasAsOpcoes = listOf(
            cbSaude, cbEducacao, cbTransporte, cbViolencia,
            cbMeioAmbiente, cbTecnologia, cbArborizacao, cbEconomia, cbNatalidade
        )

        btnConfirmar.setOnClickListener {
            val problemasSelecionados = mutableListOf<String>()

            todasAsOpcoes.forEach { checkbox ->
                if (checkbox.isChecked) {
                    problemasSelecionados.add(checkbox.text.toString().trim())
                }
            }

            if (problemasSelecionados.isEmpty()) {
                Toast.makeText(this, "Por favor, selecione pelo menos um problema.", Toast.LENGTH_SHORT).show()
            } else {
                val votoEspontaneo = intent.getStringExtra("VOTO_ESPONTANEO") ?: ""
                val votoEstimulado = intent.getStringExtra("VOTO_ESTIMULADO") ?: ""

                val problemasFormatados = problemasSelecionados.joinToString(", ")

                val intent = Intent(this, DadosEntrevistados::class.java)
                intent.putExtra("VOTO_ESPONTANEO", votoEspontaneo)
                intent.putExtra("VOTO_ESTIMULADO", votoEstimulado)
                intent.putExtra("PROBLEMAS", problemasFormatados)

                startActivity(intent)
            }
        }
    }
}