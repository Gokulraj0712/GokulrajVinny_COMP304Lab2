package com.example.gokulrajvinny_comp304lab2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/***
 * Submitted by:
 *               Gokulraj Venugopal - 301202722
 *               Vinny Mariam Vinu -  301234317
 */


class PaymentSuccessActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_successful_screen)

        val enterButton = findViewById<Button>(R.id.back_to_home_button)
        enterButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do you want to go back?")
        builder.setMessage("Going back can cancel the transaction ")
        builder.setPositiveButton("Yes") { _, _ ->
            super.onBackPressed()
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }
}