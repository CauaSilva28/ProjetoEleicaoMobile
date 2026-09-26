package com.example.sistemaeleicaop1

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sistemaeleicaop1.R
import com.seudominio.sistemaeleicaop1.Problemas

class Candidatos : AppCompatActivity() {
    private var votoEstimulado: String = ""
    private var layoutSelecionado: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidatos)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btnBranco = findViewById<Button>(R.id.btnBranco)
        val btnNulo = findViewById<Button>(R.id.btnNulo)
        val btnNaoSei = findViewById<Button>(R.id.btnNaoSei)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)

        btn1.setOnClickListener { selecionarOpcao(it as Button, "Jason") }
        btn2.setOnClickListener { selecionarOpcao(it as Button, "Thanos") }
        btn3.setOnClickListener { selecionarOpcao(it as Button, "Darth Vader") }
        btn4.setOnClickListener { selecionarOpcao(it as Button, "Coringa") }
        btn5.setOnClickListener { selecionarOpcao(it as Button, "Godzilla") }
        btnBranco.setOnClickListener { selecionarOpcao(it as Button, "Branco") }
        btnNulo.setOnClickListener { selecionarOpcao(it as Button, "Nulo") }
        btnNaoSei.setOnClickListener { selecionarOpcao(it as Button, "Não Sei") }

        btnConfirmar.setOnClickListener {
            if (votoEstimulado.isEmpty()) {
                Toast.makeText(this, "Por favor, selecione uma opção antes de confirmar.", Toast.LENGTH_SHORT).show()
            } else {
                val votoEspontaneo = intent.getStringExtra("VOTO_ESPONTANEO") ?: ""

                val intent = Intent(this, Problemas::class.java)

                intent.putExtra("VOTO_ESPONTANEO", votoEspontaneo)
                intent.putExtra("VOTO_ESTIMULADO", votoEstimulado)

                startActivity(intent)
            }
        }
    }

    private fun selecionarOpcao(botaoClicado: Button, nomeVoto: String) {

        val layoutOpcao = (botaoClicado.parent as View).parent as LinearLayout

        layoutSelecionado?.background = null

        val bordaSelecionada = GradientDrawable()
        bordaSelecionada.setStroke(6, Color.YELLOW)
        bordaSelecionada.cornerRadius = 16f

        layoutOpcao.background = bordaSelecionada

        layoutSelecionado = layoutOpcao
        votoEstimulado = nomeVoto
    }
}