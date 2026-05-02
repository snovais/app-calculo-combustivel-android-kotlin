package com.sergio.calculocombus

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. ANIMAÇÃO DE ENTRADA: Deve vir sempre ANTES do super.onCreate
        // Isso utiliza o ícone do seu app para criar uma transição suave
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. MAPEAMENTO: Conectando a interface XML ao código Kotlin
        val editDistancia = findViewById<EditText>(R.id.editDistancia)
        val editPrecoLitro = findViewById<EditText>(R.id.editPrecoLitro)
        val editConsumo = findViewById<EditText>(R.id.editConsumo)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        // 3. EVENTO DE CLIQUE: Onde a mágica acontece
        btnCalcular.setOnClickListener {
            val strDistancia = editDistancia.text.toString()
            val strPreco = editPrecoLitro.text.toString()
            val strConsumo = editConsumo.text.toString()

            // Validação simples: os campos não podem estar vazios
            if (strDistancia.isNotEmpty() && strPreco.isNotEmpty() && strConsumo.isNotEmpty()) {
                val distancia = strDistancia.toDouble()
                val preco = strPreco.toDouble()
                val consumo = strConsumo.toDouble()

                // Cálculo da lógica de negócio
                if (consumo > 0) {
                    val custoTotal = (distancia / consumo) * preco

                    // Exibe o resultado formatado
                    txtResultado.text = String.format("Custo Estimado: R$ %.2f", custoTotal)

                    // 4. LIMPEZA DOS CAMPOS: Melhora a experiência do usuário (UX)
                    // Após o cálculo, limpamos as entradas para facilitar uma nova consulta
                    editDistancia.text.clear()
                    editPrecoLitro.text.clear()
                    editConsumo.text.clear()

                    // Remove o foco do último campo para o teclado não atrapalhar a visão do resultado
                    editConsumo.clearFocus()

                } else {
                    txtResultado.text = "O consumo deve ser maior que zero!"
                }
            } else {
                txtResultado.text = "Preencha todos os campos!"
            }
        }
    }
}