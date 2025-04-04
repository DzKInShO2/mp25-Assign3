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
            // Get all inputs in the form of string
            val emailString = findViewById<EditText>(R.id.emailTextEdit).text.toString()
            val passwordString = findViewById<EditText>(R.id.passwordTextEdit).text.toString()

            // Get SharedPreference cache
            var cache = getSharedPreferences("UserData", MODE_PRIVATE)

            // Check if account existed using given email as key by checking
            // the returned value from the getString function is equal to
            // null-termination character
            val cacheData = cache.getString(emailString, "\u0000")
            if (cacheData == "\u0000") {
                Toast.makeText(this, "User by email $emailString doesn't exist", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Since the stored data is separated with \u0000 then we would like
            // to split it to get the user data. After that we constructed a new
            // UserData object using the returned value
            val splittedData = cacheData?.split("\u0000")
            val userData = UserData(splittedData?.get(0).toString(), emailString, splittedData?.get(1).toString(), splittedData?.get(2).toString())

            // Check if password is correct by comparing it with the stored password
            if (passwordString != userData.password) {
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Login successfully.", Toast.LENGTH_SHORT).show()

            // Sent the user data to landing page with parcelable
            val landingPageIntent = Intent(this, LandingActivity::class.java).apply {
                putExtra("user", userData)
            }

            // Go to landing page after the account is verified
            startActivity(landingPageIntent)
        }
    }
}