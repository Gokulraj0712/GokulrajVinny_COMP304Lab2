package com.example.gokulrajvinny_comp304lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    private lateinit var homeRadioGroup: RadioGroup
    private lateinit var paymentButton: Button
    private var selectedHome: String? = null
    private var selectedHomes: List<AvailableHomesActivity.Home> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_screen)

            //selectedHomes = intent.getSerializableExtra("selected_homes") as List<AvailableHomesActivity.Home>

        // Get homes from preferences
        val preferences = getSharedPreferences("homes", MODE_PRIVATE)
        val homes = preferences.getStringSet("selected_homes", emptySet())

        homeRadioGroup = findViewById(R.id.homes_radio_group)
        paymentButton = findViewById(R.id.payment_options_button)

        // Dynamically add radio buttons for each home
        homes?.forEach { home ->
            val radioButton = RadioButton(this)
            radioButton.text = home
            radioButton.setOnClickListener { view ->
                selectedHome = home
                paymentButton.isEnabled = true
            }
            homeRadioGroup.addView(radioButton)
        }

        // Set onClickListener for payment button
        paymentButton.setOnClickListener {
            // Start PaymentActivity and pass selected home as extra
            val intent = Intent(this, PaymentOptionsActivity::class.java)
            intent.putExtra("selected_home", selectedHome)
            startActivity(intent)
        }
    }
}
