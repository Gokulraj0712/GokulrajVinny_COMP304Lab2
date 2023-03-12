package com.example.gokulrajvinny_comp304lab2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.internal.wait

class PaymentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var cardEditText: EditText
    private lateinit var securityCodeEditText: EditText
    private lateinit var expDateEditText: EditText
    private lateinit var sportRadioGroup: RadioGroup
    private lateinit var teamSpinner: Spinner
    private lateinit var foodSpinner: Spinner
    private lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_screen)

        nameEditText = findViewById(R.id.nameEditText)
        cardEditText = findViewById(R.id.cardEditText)
        securityCodeEditText = findViewById(R.id.secuirtycode)
        expDateEditText = findViewById(R.id.expdate)
        sportRadioGroup = findViewById(R.id.sportRadioGroup)
        teamSpinner = findViewById(R.id.teamSpinner)
        foodSpinner = findViewById(R.id.foodSpinner)
        payButton = findViewById(R.id.payButton)

        // Set up the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.teams_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            teamSpinner.adapter = adapter
        }

        // Set up the autocomplete text view
        ArrayAdapter.createFromResource(
            this,
            R.array.foods_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
               foodSpinner.setAdapter(adapter)
        }

        payButton.setOnClickListener {
            if (isValidInput()) {

                    Toast.makeText(this, "Payment Success", Toast.LENGTH_LONG).show()
                    Thread.sleep(3000)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validate user input
    private fun isValidInput(): Boolean {
        return nameEditText.text.isNotBlank() &&
                cardEditText.text.isNotBlank() &&
                securityCodeEditText.text.isNotBlank() &&
                expDateEditText.text.isNotBlank() &&
                sportRadioGroup.checkedRadioButtonId != -1
    }
}