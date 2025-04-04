package com.kick.mp25_assign3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get user data from parcelable
        val userData = intent.getParcelableExtra<UserData>("user")

        // Display it when userData is not null
        if (userData != null) {
            var nameText = findViewById<TextView>(R.id.nameText)
            var informationText = findViewById<TextView>(R.id.informationText)
            nameText.text = userData.name
            informationText.text = userData.information
        }
    }
}