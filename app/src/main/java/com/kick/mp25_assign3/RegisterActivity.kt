package com.kick.mp25_assign3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginPageIntent = Intent(this, LoginActivity::class.java)

        findViewById<Button>(R.id.registerButton).setOnClickListener {
            val newNameString = findViewById<EditText>(R.id.newNameTextEdit).text.toString()
            val newEmailString = findViewById<EditText>(R.id.newEmailTextEdit).text.toString()
            val newPasswordString = findViewById<EditText>(R.id.newPasswordTextEdit).text.toString()
            val verifyPasswordString = findViewById<EditText>(R.id.verifyPasswordTextEdit).text.toString()

            if (newNameString.isEmpty() && newEmailString.isEmpty()
                && newPasswordString.isEmpty() && verifyPasswordString.isEmpty()) {
                Toast.makeText(this, "Please fill out all inputs.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newEmailString in Singleton.userDatas) {
                Toast.makeText(this, "Email provided had been used.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!newEmailString.matches(Regex(".+@.*..+"))) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPasswordString != verifyPasswordString) {
                Toast.makeText(this, "Given passwords doesn't match.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Singleton.userDatas[newEmailString] = UserData(newNameString, newEmailString, newPasswordString)

            Toast.makeText(this, "User has been registered successfully", Toast.LENGTH_SHORT).show()

            startActivity(loginPageIntent)
        }
    }
}