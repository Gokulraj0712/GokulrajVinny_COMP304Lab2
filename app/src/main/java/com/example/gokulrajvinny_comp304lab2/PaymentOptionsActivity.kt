package com.example.gokulrajvinny_comp304lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentOptionsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_options_screen)

        val cashRadioButton = findViewById<RadioButton>(R.id.cash_radio_button)
        val creditCardRadioButton = findViewById<RadioButton>(R.id.credit_card_radio_button)
        val debitCardRadioButton = findViewById<RadioButton>(R.id.debit_card_radio_button)
        val submitButton = findViewById<Button>(R.id.submit_button)
        

        submitButton.setOnClickListener {
            if(cashRadioButton.isChecked || creditCardRadioButton.isChecked || debitCardRadioButton.isChecked)
            {
                if (cashRadioButton.isChecked) {
                    Toast.makeText(this, getString(R.string.pay_success), Toast.LENGTH_LONG).show()
                    val intent = Intent(this, PaymentSuccessActivity::class.java)
                    Thread.sleep(3000)
                    startActivity(intent)
                } else if (creditCardRadioButton.isChecked || debitCardRadioButton.isChecked) {
                    val intent = Intent(this, PaymentActivity::class.java)
                    startActivity(intent)
                }
            }
            else
            {
                Toast.makeText(this,"Select Any Payment Method", Toast.LENGTH_SHORT).show()
            }
        }
    }
}