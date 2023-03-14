package com.example.gokulrajvinny_comp304lab2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gokulrajvinny_comp304lab2.adapter.HomeAdapter
import java.io.Serializable


/***
 * Submitted by:
 *               Gokulraj Venugopal - 301202722
 *               Vinny Mariam Vinu -  301234317
 */

class AvailableHomesActivity : AppCompatActivity() {
    private var homes: List<Home> = emptyList()
    private lateinit var adapter: HomeAdapter
    private var selectedHomes: List<Home> = emptyList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_homes)

        val layoutManager = LinearLayoutManager(this)
        adapter = HomeAdapter(homes)

        val homeRecyclerView = findViewById<RecyclerView>(R.id.homeRecyclerView) // reference the RecyclerView by id
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.adapter = adapter

        var virtualcheck= findViewById<CheckBox>(R.id.checkBox1)
        var physicalcheck =findViewById<CheckBox>(R.id.checkBox2)


        val enterButton = findViewById<Button>(R.id.select_homes_button)
        enterButton.setOnClickListener {
            if(adapter.isAtLeastOneCheckBoxChecked())
            {
                if (((physicalcheck.isChecked) || (virtualcheck.isChecked))) {
                    selectedHomes = adapter.getSelectedHomes()
                    val intent = Intent(this, CheckoutActivity::class.java)
                    intent.putExtra("selected_homes", ArrayList(selectedHomes))
                    startActivity(intent)
                } else {
                    Toast.makeText(this, getString(R.string.ErrorHouseVisittype), Toast.LENGTH_SHORT).show()

                }
            }
            else
            {
                Toast.makeText(this, getString(R.string.Error_no_house), Toast.LENGTH_SHORT).show()

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_options_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var SelectHomeButton = findViewById<Button>(R.id.select_homes_button)
        var virtualcheck= findViewById<CheckBox>(R.id.checkBox1)
        var physicalcheck =findViewById<CheckBox>(R.id.checkBox2)
        var selecthousehint= findViewById<TextView>(R.id.houseoptionhint)
        selecthousehint.visibility=View.GONE
        when (item.itemId) {
            R.id.apartment -> {
                homes = listOf(
                    Home(getString(R.string.apartment_address1), getString(R.string.apartment_price1), R.drawable.apartment),
                    Home(getString(R.string.apartment_address2), getString(R.string.apartment_price2), R.drawable.apartment),
                    Home(getString(R.string.apartment_address3), getString(R.string.apartment_price3), R.drawable.apartment),
                    // Add more apartments as needed
                )
                SelectHomeButton.visibility = View.VISIBLE
                virtualcheck.visibility=View.VISIBLE
                physicalcheck.visibility = View.VISIBLE

                if (::adapter.isInitialized) {
                    adapter.updateData(homes)
                }
                return true
            }
            R.id.detached_home -> {
                homes = listOf(
                    Home(getString(R.string.home_address1), getString(R.string.home_price1), R.drawable.homeicon),
                    Home(getString(R.string.home_address2), getString(R.string.home_price2), R.drawable.homeicon),
                    Home(getString(R.string.home_address3), getString(R.string.home_price3), R.drawable.homeicon),
                    // Add more detached homes as needed
                )
                SelectHomeButton.visibility = View.VISIBLE
                virtualcheck.visibility=View.VISIBLE
                physicalcheck.visibility = View.VISIBLE

                if (::adapter.isInitialized) {
                    adapter.updateData(homes)
                }
                return true
            }
            R.id.semi_detached_home -> {
                homes = listOf(
                    Home(getString(R.string.semi_address1), getString(R.string.semi_price1), R.drawable.semihouse),
                    Home(getString(R.string.semi_address2), getString(R.string.semi_price2), R.drawable.semihouse),
                    Home(getString(R.string.semi_address3), getString(R.string.semi_price3), R.drawable.semihouse),
                    // Add more semi-detached homes as needed
                )
                SelectHomeButton.visibility = View.VISIBLE
                virtualcheck.visibility=View.VISIBLE
                physicalcheck.visibility = View.VISIBLE

                if (::adapter.isInitialized) {
                    adapter.updateData(homes)
                }
                return true
            }
            R.id.condominium_apartment -> {
                homes = listOf(
                    Home(getString(R.string.condo_address1), getString(R.string.condo_price1), R.drawable.condo),
                    Home(getString(R.string.condo_address2), getString(R.string.condo_price2), R.drawable.condo),
                    Home(getString(R.string.condo_address3), getString(R.string.condo_price3), R.drawable.condo),
                    // Add more condominium apartments as needed
                )
                SelectHomeButton.visibility = View.VISIBLE
                virtualcheck.visibility=View.VISIBLE
                physicalcheck.visibility = View.VISIBLE

                if (::adapter.isInitialized) {
                    adapter.updateData(homes)
                }
                return true
            }

            R.id.town_house -> {
                homes = listOf(
                    Home(getString(R.string.townhouse_address1), getString(R.string.townhouse_price1), R.drawable.townhouse),
                    Home(getString(R.string.townhouse_address2), getString(R.string.townhouse_price2), R.drawable.townhouse),
                    Home(getString(R.string.townhouse_address3), getString(R.string.townhouse_price3), R.drawable.townhouse),
                    // Add more townhouse apartments as needed
                )
                SelectHomeButton.visibility = View.VISIBLE
                virtualcheck.visibility=View.VISIBLE
                physicalcheck.visibility = View.VISIBLE

                if (::adapter.isInitialized) {
                    adapter.updateData(homes)
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    data class Home(val address: String, val price: String, val imageResourceId: Int) : java.io.Serializable



}
