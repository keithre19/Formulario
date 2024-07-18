package com.example.formulario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = findViewById<EditText>(R.id.txtNombre)
        val apellido = findViewById<EditText>(R.id.txtApellido)
        val telefono = findViewById<EditText>(R.id.txtTelefono)
        val edad = findViewById<EditText>(R.id.txtEdad)
        val correo = findViewById<EditText>(R.id.txtCorreo)

        val enviarDatos = findViewById<Button>(R.id.btnIngresar)
        enviarDatos.setOnClickListener{
            if(nombre.text.toString().isEmpty()){
                nombre.error = "Debe llenar este campo"
            } else if(apellido.text.toString().isEmpty()){
                apellido.error = "Debe llenar este campo"
            } else if(telefono.text.toString().isEmpty()){
                telefono.error = "Debe llenar este campo"
            } else if(edad.text.toString().isEmpty()){
                edad.error = "Debe llenar este campo"
            } else if(correo.text.toString().isEmpty()){
                correo.error = "Debe llenar este campo"
            } else {
                val enviarDatosIntent = Intent(this, ReceiveDataForm::class.java).apply{
                    putExtra("nombre", nombre.text.toString())
                    putExtra("apellido", apellido.text.toString())
                    putExtra("telefono", telefono.text.toString())
                    putExtra("edad", edad.text.toString())
                    putExtra("correo", correo.text.toString())
                }
                startActivity(enviarDatosIntent)
            }
        }
        
    }
}