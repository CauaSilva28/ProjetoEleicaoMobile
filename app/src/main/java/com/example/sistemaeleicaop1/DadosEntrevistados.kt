package com.example.sistemaeleicaop1

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sistemaeleicaop1.DadosGlobais // Importe o seu DadosGlobais
import com.example.sistemaeleicaop1.Eleitor      // Importe o seu Eleitor
import com.example.sistemaeleicaop1.Espontanea // Importe a tela Espontanea
import com.example.sistemaeleicaop1.R          // Importe o R do seu projeto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.location.Geocoder

class DadosEntrevistados : AppCompatActivity() {

    // Código numérico para identificar o pedido de permissão do GPS
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_entrevistados)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etCelular = findViewById<EditText>(R.id.etCelular)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)

        // Pede permissão de localização para o usuário assim que a tela abre
        pedirPermissaoLocalizacao()

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
            // 1. Verifica se está vazio e aplica o "Não informado"
            val nomeBruto = etNome.text.toString().trim()
            val celularBruto = etCelular.text.toString().trim()

            val nomeEntrevistado = if (nomeBruto.isNotEmpty()) nomeBruto else "Não informado"
            val celularEntrevistado = if (celularBruto.isNotEmpty()) celularBruto else "Não informado"

            // 2. Captura a Data e Hora exatas do momento do clique
            val formatadorDataHora = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val dataHoraAtual = formatadorDataHora.format(Date())

            // 3. Captura a Localização
            val localizacaoAtual = obterLocalizacao()

            // 4. Salva o eleitor na nossa lista global
            val novoEleitor = Eleitor(
                nome = nomeEntrevistado,
                celular = celularEntrevistado,
                dataHora = dataHoraAtual,
                localizacao = localizacaoAtual
            )
            DadosGlobais.adicionarEleitor(novoEleitor)

            Toast.makeText(this, "Entrevista finalizada e salva com sucesso!", Toast.LENGTH_LONG).show()

            // 5. Retorna para a tela inicial
            val intent = Intent(this, Espontanea::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    // Função que solicita a permissão de GPS na tela
    private fun pedirPermissaoLocalizacao() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    // Função que pega a localização e converte para Cidade - Estado
    private fun obterLocalizacao(): String {
        var localizacaoStr = "Localização indisponível"

        // Verifica se o usuário deu a permissão
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            // Tenta pegar a última localização conhecida
            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if (location != null) {
                try {
                    // Inicia o Geocoder para traduzir Lat/Lon em Endereço
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val enderecos = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                    if (!enderecos.isNullOrEmpty()) {
                        val endereco = enderecos[0]

                        // locality ou subAdminArea costumam guardar a cidade
                        val cidade = endereco.locality ?: endereco.subAdminArea ?: "Cidade Desconhecida"

                        // adminArea guarda o Estado (SP, RJ, MG, etc.)
                        val estado = endereco.adminArea ?: ""

                        localizacaoStr = if (estado.isNotEmpty()) {
                            "$cidade - $estado"
                        } else {
                            cidade
                        }
                    } else {
                        // Se não conseguir traduzir, mostra a Latitude e Longitude
                        localizacaoStr = "Lat: ${String.format("%.4f", location.latitude)}, Lon: ${String.format("%.4f", location.longitude)}"
                    }
                } catch (e: Exception) {
                    // Se der erro (ex: sem internet), mostra a Latitude e Longitude
                    localizacaoStr = "Lat: ${String.format("%.4f", location.latitude)}, Lon: ${String.format("%.4f", location.longitude)}"
                }
            }
        } else {
            localizacaoStr = "Permissão de GPS negada"
        }

        return localizacaoStr
    }
}