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
            val newNameText = findViewById<EditText>(R.id.newNameTextEdit).text.toString()
            val newEmailText = findViewById<EditText>(R.id.newEmailTextEdit).text.toString()
            val newPasswordText = findViewById<EditText>(R.id.newPasswordTextEdit).text.toString()
            val verifyPasswordText = findViewById<EditText>(R.id.verifyPasswordTextEdit).text.toString()

            if (newNameText.isEmpty() && newEmailText.isEmpty()
                && newPasswordText.isEmpty() && verifyPasswordText.isEmpty()) {
                Toast.makeText(this, "Please fill out all inputs.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newEmailText in UserData.userLists) {
                Toast.makeText(this, "Email provided had been used.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!newEmailText.matches(Regex(".+@.*..+"))) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPasswordText != verifyPasswordText) {
                Toast.makeText(this, "Given passwords doesn't match.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UserData.userLists[newEmailText] = listOf<String>(
                newNameText,
                newPasswordText
            )

            Toast.makeText(this, "User has been registered successfully", Toast.LENGTH_SHORT).show()

            startActivity(loginPageIntent)
        }
    }
}