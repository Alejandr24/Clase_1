package com.example.clase_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinnerExample()
    }

    fun spinnerExample(){
        val operations = resources.getStringArray(R.array.operation_array)
        val elementos = listOf("Elemento 1", "Elemento 2", "Elemento 3")

        val spinner: Spinner = findViewById(R.id.operatorSpinner)
        val number1: EditText = findViewById(R.id.number1EditText)
        val number2: EditText = findViewById(R.id.number2EditText)

        // Crea un ArrayAdapter usando los elementos y el diseño predeterminado para el spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)

        // Especifica el diseño que se usará cuando se desplieguen las opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Une el ArrayAdapter al Spinner
        spinner.adapter = adapter

        // Opcionalmente, puedes configurar un escuchador para detectar la selección del usuario
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemSeleccionado = operations[position]
                val resultado = Operations(itemSeleccionado, number1.text.toString().toDouble(),number2.text.toString().toDouble())

                // Realiza alguna acción con el elemento seleccionado
                Toast.makeText(this@MainActivity, "Resultado: $resultado", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Se llama cuando no se ha seleccionado nada en el Spinner (opcional)
                Toast.makeText(this@MainActivity, "Nada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun Operations(operation: String, num1: Double, num2: Double): Double {
        return when (operation) {
            "Sum" -> num1 + num2
            "Subtract" -> num1 - num2
            "Multiply" -> num1 * num2
            "Divide" -> num1 / num2
            else -> throw IllegalArgumentException("operacion invalida")
        }
    }
}