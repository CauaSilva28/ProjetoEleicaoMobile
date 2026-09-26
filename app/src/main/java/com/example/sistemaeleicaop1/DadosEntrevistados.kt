package com.seudominio.sistemaeleicaop1 // Substitua pelo nome do seu pacote

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sistemaeleicaop1.Espontanea
import com.example.sistemaeleicaop1.R

class DadosEntrevistados : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_entrevistados)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etCelular = findViewById<EditText>(R.id.etCelular)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)

        val votoEspontaneo = intent.getStringExtra("VOTO_ESPONTANEO") ?: ""
        val votoEstimulado = intent.getStringExtra("VOTO_ESTIMULADO") ?: ""
        val problemas = intent.getStringExtra("PROBLEMAS") ?: ""

        btnConfirmar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            val celular = etCelular.text.toString().trim()

            if (nome.isNotEmpty() || celular.isNotEmpty()) {
                Toast.makeText(this, "Dados opcionais registrados!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nenhum dado informado (Opcional).", Toast.LENGTH_SHORT).show()
            }
        }

        btnFinalizar.setOnClickListener {
            val nomeEntrevistado = etNome.text.toString().trim()
            val celularEntrevistado = etCelular.text.toString().trim()

            Toast.makeText(this, "Entrevista finalizada e salva com sucesso!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, Espontanea::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)
        }
    }
}