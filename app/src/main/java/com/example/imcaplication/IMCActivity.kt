package com.example.imcaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    // Variables
    private var isManSelected: Boolean = true
    private var isWomanSelected: Boolean = false
    private var currentHeight: Int = 100
    private var currentWeight: Int = 40
    private var currentAge: Int = 13

    // Componentes
    private lateinit var viewH: CardView
    private lateinit var viewM: CardView
    private lateinit var tvAltura: TextView
    private lateinit var rsAltura: RangeSlider
    private lateinit var tvPeso: TextView
    private lateinit var btnMasPeso: FloatingActionButton
    private lateinit var btnMenosPeso: FloatingActionButton
    private lateinit var tvEdad: TextView
    private lateinit var btnMasEdad: FloatingActionButton
    private lateinit var btnMenosEdad: FloatingActionButton
    private lateinit var btnCalcular: Button

    // Key
    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)

        // Función para inicializar los componentes
        initComponent()
        // Función para saber cuando nuestro componente es pulsado
        initListeners()
        // Función para Inicializar la UI
        initUI()
    }

    // Función que se ejecutará al entrar en la aplicación
    private fun initUI() {
        // Llamanos a la función de color
        setGenderColor()
        // Llamamos a la función para que se muestre el peso
        setWeight()
        // Llamamos a la función para que se muestre la edad
        setAge()
    }

    // Función para inicializar los componentes
    private fun initComponent() {
        viewH = findViewById(R.id.viewH)
        viewM = findViewById(R.id.viewM)
        tvAltura = findViewById(R.id.tvAltura)
        rsAltura = findViewById(R.id.rsAltura)
        tvPeso = findViewById(R.id.tvPeso)
        btnMasPeso = findViewById(R.id.btnMasPeso)
        btnMenosPeso = findViewById(R.id.btnMenosPeso)
        tvEdad = findViewById(R.id.tvEdad)
        btnMasEdad = findViewById(R.id.btnMasEdad)
        btnMenosEdad = findViewById(R.id.btnMenosEdad)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    // CardView seleccionada
    private fun initListeners() {
        // CardView Hombre-Mujer
        viewH.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewM.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        // RangeSlider
        rsAltura.addOnChangeListener { _, value, _ -> // Los valores que no se utilizan se colocan como "_"
            // Formateamos el valor de "value"l
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvAltura.text = "$currentHeight cm"
        }

        // CardView Peso
        btnMasPeso.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnMenosPeso.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        // CardViewEdad
        btnMasEdad.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnMenosEdad.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        // Botón Calcular
        btnCalcular.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    // Cambio de texto del elemento CardView "PESO"
    private fun setWeight() {
        tvPeso.text = currentWeight.toString()
    }

    // Cambio de texto del elemento CardView "EDAD"
    private fun setAge() {
        tvEdad.text = currentAge.toString()
    }

    // Cambio de color del elemento CardView seleccionado
    private fun setGenderColor() {
        viewH.setCardBackgroundColor(getBackgroundColor(isManSelected))
        viewM.setCardBackgroundColor(getBackgroundColor(isWomanSelected))
    }

    // Cambiamos el valor de las variables "Honmbre" o "Mujer"
    private fun changeGender() {
        isManSelected = !isManSelected
        isWomanSelected = !isWomanSelected
    }

    // Elemento CardView seleccionado
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.black
        } else {
            R.color.greyF
        }

        return ContextCompat.getColor(this, colorReference)
    }

    // Calcular el IMC
    private fun calculateIMC(): Double {
        var df = DecimalFormat("#.##")
        var imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    // Resultado del IMC
    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }
}