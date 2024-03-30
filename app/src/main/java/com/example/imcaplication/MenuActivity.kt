package com.example.imcaplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import android.content.Intent

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // declaramos el botón "IMC"
        val btnIMCApp = findViewById<AppCompatButton>(R.id.btnIMCApp)

        // Botón IMC
        btnIMCApp.setOnClickListener { navigateIMCApp() }
    }

    // Ingresar a la aplicación
    private fun navigateIMCApp() {
        val intent = Intent(this, IMCActivity::class.java)
        startActivity(intent)
    }
}