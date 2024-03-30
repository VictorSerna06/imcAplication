package com.example.imcaplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.imcaplication.IMCActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    // Variables
    private lateinit var tvResultado: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var btnRecalcular: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        // Se recibe el "resultado" del IMC
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        // Inicializamos los componentes
        initComponets()
        initUI(result)
        initListeners()
    }

    // Componentes
    private fun initComponets() {
        tvResultado = findViewById(R.id.tvResultado)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        btnRecalcular = findViewById(R.id.btnRecalcular)
    }

    private fun initUI(result: Double) {
        // Resultado del IMC
        tvIMC.text = result.toString()

        when (result) {
            // Parametros de IMC
            in 0.00..18.50 -> { // Bajo peso
                tvResultado.text = getString(R.string.titulo_bajo_peso)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescripcion.text = getString(R.string.descripcion_bajo_peso)
            }

            in 18.51..24.99 -> { // Peso normal
                tvResultado.text = getString(R.string.titulo_peso_normal)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.normal))
                tvDescripcion.text = getString(R.string.descripcio_peso_normal)
            }

            in 25.00..29.99 -> { // Sobre peso
                tvResultado.text = getString(R.string.titulo_sobrepeso)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                tvDescripcion.text = getString(R.string.descripcion_sobrepeso)
            }

            in 30.00..99.00 -> { // Obesidad
                tvResultado.text = getString(R.string.titulo_obesidad)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescripcion.text = getString(R.string.descripcion_obesidad)
            }

            // Si el valor es Null nos devolvera un error
            else -> {
                tvResultado.text = getString(R.string.error)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvIMC.text = getString(R.string.error)
                tvDescripcion.text = getString(R.string.error)
            }
        }
    }

    // Botón Re-Calcular
    private fun initListeners() {
        btnRecalcular.setOnClickListener { onBackPressed() }
    }
}