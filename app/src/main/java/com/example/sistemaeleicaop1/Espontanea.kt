package com.example.sistemaeleicaop1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sistemaeleicaop1.Candidatos

class Espontanea : AppCompatActivity() {
    private lateinit var etCandidato: EditText
    private lateinit var btnConfirmar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espontanea)

        etCandidato = findViewById(R.id.etCandidato)
        btnConfirmar = findViewById(R.id.btnConfirmar)

        btnConfirmar.setOnClickListener {
            val votoEspontaneo = etCandidato.text.toString().trim()

            val intent = Intent(this, Candidatos::class.java)

            intent.putExtra("VOTO_ESPONTANEO", votoEspontaneo)

            startActivity(intent)
        }
    }
}