package com.example.motosventa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btningresar : Button = findViewById(R.id.btnIngresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val txtpass : TextView = findViewById(R.id.edtPassword)
        val btncrear_Cuenta_Nueva : TextView = findViewById(R.id.btnCrearCuenta)
        val btnRecordar : TextView = findViewById(R.id.btnOlvidar)
        firebaseAuth= Firebase.auth
        btningresar.setOnClickListener()
        {
            sign(txtemail.text.toString(), txtpass.text.toString() )
        }
        btncrear_Cuenta_Nueva.setOnClickListener()
        {
            val i = Intent(this, CrearCuentaActivity3::class.java)
            startActivity(i)
        }
        btnRecordar.setOnClickListener()
        {
            val i = Intent(this, RecordarPassActivity3::class.java)
            startActivity(i)
        }

    }
    private fun sign(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext,"Inicio de Sesión Correcto",Toast.LENGTH_SHORT).show()
                //aquí vamos a la segunda página
                val i = Intent (this, MainActivity2::class.java)
                startActivity(i)

             }
            else
            {
                Toast.makeText(baseContext,"Error del Email y/o Contraseña",Toast.LENGTH_SHORT).show()
            }

        }

    }


}