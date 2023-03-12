package com.example.gokulrajvinny_comp304lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterButton = findViewById<Button>(R.id.start)
        enterButton.setOnClickListener {
            val intent = Intent(this, AvailableHomesActivity::class.java)
            startActivity(intent)
        }
    }
}