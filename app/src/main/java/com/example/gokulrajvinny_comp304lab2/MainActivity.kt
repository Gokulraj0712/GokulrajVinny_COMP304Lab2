package com.example.gokulrajvinny_comp304lab2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/***
 * Submitted by:
 *               Gokulraj Venugopal - 301202722
 *               Vinny Mariam Vinu -  301234317
 */

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

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit app")
        builder.setMessage("Do you want to exit?")
        builder.setPositiveButton("Yes") { _, _ -> finishAffinity() }
        builder.setNegativeButton("No", null)
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}