package com.example.sistemaeleicaop1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnEleitores : Button
    private lateinit var btnResultado : Button
    private lateinit var btnLimparDados : Button
    private lateinit var btnSair : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnEleitores = findViewById(R.id.btnEleitores)
        btnResultado = findViewById(R.id.btnResultado)
        btnLimparDados = findViewById(R.id.btnLimparDados)
        btnSair = findViewById(R.id.btnSair)

        btnEleitores.setOnClickListener {
            val intent = Intent(this, Eleitores::class.java)
            startActivity(intent)
        }

        btnResultado.setOnClickListener {
            val intent = Intent(this, Resultado::class.java)
            startActivity(intent)
        }

        btnLimparDados.setOnClickListener {

        }

        btnSair.setOnClickListener {
            finishAffinity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}