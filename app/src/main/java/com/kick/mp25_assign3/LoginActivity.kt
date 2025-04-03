package com.kick.mp25_assign3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val emailText = findViewById<EditText>(R.id.emailTextEdit).text.toString()
            val passwordText = findViewById<EditText>(R.id.passwordTextEdit).text.toString()

            if (emailText !in UserData.userLists || UserData.userLists[emailText]?.get(1) != passwordText) {
                Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Login successfully.", Toast.LENGTH_SHORT).show()
        }
    }
}