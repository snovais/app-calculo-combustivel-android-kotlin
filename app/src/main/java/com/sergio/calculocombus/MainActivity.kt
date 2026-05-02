// Define o identificador único deste projeto; essencial para o Android organizar o app no sistema
package com.sergio.calculocombus

// Importação de bibliotecas básicas do Android para manipular componentes e ciclo de vida
import android.os.Bundle // Objeto que salva o estado da instância da tela
import android.widget.Button // Importa o componente de botão da interface
import android.widget.EditText // Importa campos de entrada de texto
import android.widget.TextView // Importa campos de exibição de texto
import androidx.appcompat.app.AppCompatActivity // Classe base que dá suporte a versões antigas do Android

// Declaração da classe principal que representa a tela do app (Activity)
class MainActivity : AppCompatActivity() {

    // Método que o Android chama automaticamente quando a tela é criada pela primeira vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Executa a lógica padrão de criação da classe pai

        // Conecta o arquivo Kotlin ao arquivo visual (XML); "infla" a interface na memória
        setContentView(R.layout.activity_main)

        // MAPEAMENTO: Busca no XML os componentes pelos seus IDs e os vincula a variáveis Kotlin
        // Intenção: Criar a ponte entre o visual (UI) e o código de execução
        val editDistancia = findViewById<EditText>(R.id.editDistancia)
        val editPrecoLitro = findViewById<EditText>(R.id.editPrecoLitro)
        val editConsumo = findViewById<EditText>(R.id.editConsumo)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        // EVENT LISTENER: Define uma função que ficará "vigiando" o clique no botão
        btnCalcular.setOnClickListener {

            // Captura o conteúdo dos campos de entrada e os transforma em String pura
            val strDistancia = editDistancia.text.toString()
            val strPreco = editPrecoLitro.text.toString()
            val strConsumo = editConsumo.text.toString()

            // VALIDAÇÃO: Verifica se o usuário não deixou nenhum campo vazio antes de calcular
            if (strDistancia.isNotEmpty() && strPreco.isNotEmpty() && strConsumo.isNotEmpty()) {

                // CONVERSÃO (Parsing): Transforma o texto (String) em números decimais (Double)
                // Intenção: Preparar os dados para operações matemáticas
                val distancia = strDistancia.toDouble()
                val preco = strPreco.toDouble()
                val consumo = strConsumo.toDouble()

                // LÓGICA DE NEGÓCIO: Evita erro matemático de divisão por zero
                if (consumo > 0) {
                    // Cálculo: (Distância / Consumo) nos dá a litragem, que multiplicamos pelo preço
                    val custoTotal = (distancia / consumo) * preco

                    // SAÍDA DE DADOS: Formata o valor final como moeda (2 casas decimais) e exibe na tela
                    txtResultado.text = String.format("Custo Estimado: R$ %.2f", custoTotal)
                } else {
                    // Feedback visual caso o consumo inserido seja inválido
                    txtResultado.text = "O consumo deve ser maior que zero!"
                }
            } else {
                // Feedback visual para garantir que o usuário preencha todo o formulário
                txtResultado.text = "Preencha todos os campos!"
            }
        }
    }
}