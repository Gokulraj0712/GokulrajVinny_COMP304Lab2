package com.example.gokulrajvinny_comp304lab2

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
        //val preferences = getSharedPreferences("homes", MODE_PRIVATE)
        //val intent = Intent(this, PaymentOptionsActivity::class.java)
        //val homes = intent.getStringArrayExtra("selected_homes")
        //println("Checkout Activty"+homes)

        selectedHomes = (intent.getSerializableExtra("selected_homes") as? ArrayList<AvailableHomesActivity.Home>)!!


        homeRadioGroup = findViewById(R.id.homes_radio_group)
        paymentButton = findViewById(R.id.payment_options_button)

        // Dynamically add radio buttons for each home
        selectedHomes?.forEach { home ->
            val radioButton = RadioButton(this)
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            radioButton.text = home.address.toString() + " " + home.price.toString()
            radioButton.setOnClickListener { view ->
                selectedHome = home.toString()
                paymentButton.isEnabled = true
            }
            homeRadioGroup.addView(radioButton)
        }

        // Set onClickListener for payment button
        paymentButton.setOnClickListener {
            val selectedRadioButtonId = homeRadioGroup.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                // Display error message
                Toast.makeText(this, getString(R.string.selecthome), Toast.LENGTH_SHORT).show()
            } else {
                // Start PaymentActivity and pass selected home as extra
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedHome = selectedRadioButton.text.toString()
                val intent = Intent(this, PaymentOptionsActivity::class.java)
                intent.putExtra("selected_home", selectedHome)
                startActivity(intent)
            }
        }
    }
}
