package com.example.gokulrajvinny_comp304lab2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.internal.wait
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

                    Toast.makeText(this, R.string.pay_success, Toast.LENGTH_LONG).show()
                    Thread.sleep(3000)
                    val intent = Intent(this, PaymentSuccessActivity::class.java)
                    startActivity(intent)
            } else {
                Toast.makeText(this, R.string.fill_required_fields, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validate user input
    private fun isValidInput(): Boolean {
        val name = nameEditText.text.toString()
        val cardNumber = cardEditText.text.toString()
        val securityCode = securityCodeEditText.text.toString()
        val expDate = expDateEditText.text.toString()

        if (TextUtils.isEmpty(name) || !name.matches("[a-zA-Z]+".toRegex())) {
            nameEditText.error = getString(R.string.valid_name)
            return false
        }

        if (TextUtils.isEmpty(cardNumber) || !cardNumber.matches("\\d+".toRegex()) || cardNumber.length < 16) {
            cardEditText.error = getString(R.string.valid_card)
            return false
        }

        if (TextUtils.isEmpty(securityCode) || !securityCode.matches("\\d+".toRegex()) || securityCode.length!=3) {
            securityCodeEditText.error = getString(R.string.valid_cvv)
            return false
        }

        try {
            SimpleDateFormat("MM/yy", Locale.US).parse(expDate)
        } catch (e: ParseException) {
            expDateEditText.error = getString(R.string.valid_date)
            return false
        }

        return sportRadioGroup.checkedRadioButtonId != -1
    }
}