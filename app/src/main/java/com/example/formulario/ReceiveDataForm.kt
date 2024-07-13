package com.example.formulario

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReceiveDataForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_receive_data_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var TextViewName: TextView = findViewById(R.id.textViewName)
        var TextViewLastName: TextView = findViewById(R.id.textViewLastName)
        var TextViewPhone: TextView = findViewById(R.id.textViewPhoneNumber)
        var TextViewAge: TextView = findViewById(R.id.textViewAge)
        var TextViewEmail: TextView = findViewById(R.id.textViewEmail)

        val name = intent.getStringExtra("name") ?: "No se recibió el nombre de la persona"
        val lastName = intent.getStringExtra("lastName") ?: "No se recibió el apellido"
        val phone = intent.getStringExtra("phone") ?: "No se recibió el teléfono"
        val age = intent.getStringExtra("age") ?: "No se recibió la edad"
        val email = intent.getStringExtra("email") ?: "No se recibió el correo"

        TextViewName.text = name
        TextViewLastName.text = lastName
        TextViewPhone.text = phone
        TextViewAge.text = age
        TextViewEmail.text = email
    }
}