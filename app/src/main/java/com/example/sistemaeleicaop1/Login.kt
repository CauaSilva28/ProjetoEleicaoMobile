package com.example.sistemaeleicaop1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    private lateinit var etNome : EditText

    private lateinit var etSenha : EditText

    private lateinit var btnEntrar : Button

    private lateinit var btnSair : Button

    private lateinit var tvStatus : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        etNome = findViewById(R.id.etNome)
        etSenha = findViewById(R.id.etSenha)

        btnEntrar = findViewById(R.id.btnEntrar)
        btnSair = findViewById(R.id.btnSair)

        tvStatus = findViewById(R.id.tvStatus)

        btnEntrar.setOnClickListener {
            var nome: String
            var senha: String

            nome = etNome.text.toString()
            senha = etSenha.text.toString()

            if(nome == "admin" && senha == "1234"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else if(nome == "entrevistador" && senha == "1234"){
                val intent = Intent(this, Espontanea::class.java)
                startActivity(intent)
            }
            else{
                tvStatus.text = "Login inválido"
            }
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