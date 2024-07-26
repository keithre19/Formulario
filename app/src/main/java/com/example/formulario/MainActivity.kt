package com.example.formulario

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        fetchStates()
        fetchDepartments()
        fetchCareers()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.spn_state)) { v, insets ->
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
        enviarDatos.setOnClickListener {
            if (nombre.text.toString().isEmpty()) {
                nombre.error = "Debe llenar este campo"
            } else if (apellido.text.toString().isEmpty()) {
                apellido.error = "Debe llenar este campo"
            } else if (telefono.text.toString().isEmpty()) {
                telefono.error = "Debe llenar este campo"
            } else if (edad.text.toString().isEmpty()) {
                edad.error = "Debe llenar este campo"
            } else if (correo.text.toString().isEmpty()) {
                correo.error = "Debe llenar este campo"
            } else {
                val enviarDatosIntent = Intent(this, ReceiveDataForm::class.java).apply {
                    putExtra("name", nombre.text.toString())
                    putExtra("lastName", apellido.text.toString())
                    putExtra("phone", telefono.text.toString())
                    putExtra("age", edad.text.toString())
                    putExtra("email", correo.text.toString())
                }
                startActivity(enviarDatosIntent)
            }
        }

    }


    private fun fetchStates() {
        thread {
            val url = URL("https://ff5cd2cdd378481f875700042617c96c.api.mockbin.io/")
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                val inStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val response = inStream.readText()
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.getJSONArray("Estados")
                val statesList = ArrayList<String>()
                for (i in 0 until jsonArray.length()) {
                    val state = jsonArray.getString(i) // Obtiene cada estado como un String
                    statesList.add(state)
                }
                runOnUiThread {
                    updateSpinnerState(statesList)
                }
            } finally {
                urlConnection.disconnect()
            }
        }
    }

    private fun fetchCareers() {
        thread {
            val url = URL("https://5316fa06dc0b4f758e408eab6fdc2b19.api.mockbin.io/")
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                val inStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val response = inStream.readText()
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.getJSONArray("CarrerasUniversitarias")
                val careerList = ArrayList<String>()
                for (i in 0 until jsonArray.length()) {
                    val state = jsonArray.getString(i) // Obtiene cada estado como un String
                    careerList.add(state)
                }
                runOnUiThread {
                    updateSpinnerCareer(careerList)
                }
            } finally {
                urlConnection.disconnect()
            }
        }
    }

    private fun fetchDepartments() {
        thread {
            val url = URL("https://83dafb377ca342b8a2253885670cba77.api.mockbin.io/")
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                val inStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val response = inStream.readText()
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.getJSONArray("Departamentos")
                val departmentsList = ArrayList<String>()
                for (i in 0 until jsonArray.length()) {
                    val state = jsonArray.getString(i) // Obtiene cada estado como un String
                    departmentsList.add(state)
                }
                runOnUiThread {
                    updateSpinnerDepartments(departmentsList)
                }
            } finally {
                urlConnection.disconnect()
            }
        }
    }

    private fun updateSpinnerState(statesList: ArrayList<String>) {
        val spinner: Spinner = findViewById(R.id.spn_state)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun updateSpinnerCareer(statesList: ArrayList<String>) {
        val spinner: Spinner = findViewById(R.id.spn_careers)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun updateSpinnerDepartments(statesList: ArrayList<String>) {
        val spinner: Spinner = findViewById(R.id.spn_departments)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }


}