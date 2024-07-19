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
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    var JsonCivilStatus: String = ""
    var JsonCoursesUniversity: String = ""
    var JsonDepartments: String = ""

    private fun fetchDepartments() {
    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://dipositivosmoviles.free.beeceptor.com/departments")
            .build()
        val response = client.newCall(request).execute()
        val responseData = response.body?.string()
        if (responseData != null) {
            JsonDepartments = responseData
            val gson = Gson()
            val itemsResponse = gson.fromJson(JsonDepartments, Departments::class.java)

            withContext(Dispatchers.Main) {
                val spinnerDepartments: Spinner = findViewById(R.id.spinnerDepartments)
                val adapterDepartments = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, itemsResponse.Departamentos)
                adapterDepartments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerDepartments.adapter = adapterDepartments
            }
        }
    }
}
    private fun fetchCoursesUniversity() {
    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://dipositivosmoviles.free.beeceptor.com/courses/university")
            .build()
        val response = client.newCall(request).execute()
        val responseData = response.body?.string()
        if (responseData != null) {
            JsonCoursesUniversity = responseData
            val gson = Gson()
            val itemsResponse = gson.fromJson(JsonCoursesUniversity, CoursesUniversity::class.java)

            withContext(Dispatchers.Main) {
                val spinnerCoursesUniversity: Spinner = findViewById(R.id.spinnerCoursesUniversity)
                val adapterCoursesUniversity = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, itemsResponse.Carreras)
                adapterCoursesUniversity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCoursesUniversity.adapter = adapterCoursesUniversity
            }
        }

    }
}
    private fun fetchCivilStatus() {
    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://dipositivosmoviles.free.beeceptor.com/civilstatus")
            .build()
        val response = client.newCall(request).execute()
        val responseData = response.body?.string()
        if (responseData != null) {
            JsonCivilStatus = responseData
            val gson = Gson()
            val itemsResponse = gson.fromJson(JsonCivilStatus, CivilStatus::class.java)

            withContext(Dispatchers.Main) {
                val spinnerCivilStatus: Spinner = findViewById(R.id.spinnerCivilStatus)
                val adapterCivilStatus = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, itemsResponse.Estados)
                adapterCivilStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCivilStatus.adapter = adapterCivilStatus
            }
        }
    }
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fetchCivilStatus()
        fetchCoursesUniversity()
        fetchDepartments()
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
}