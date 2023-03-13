package com.example.gokulrajvinny_comp304lab2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gokulrajvinny_comp304lab2.AvailableHomesActivity
import com.example.gokulrajvinny_comp304lab2.R

class HomeAdapter(private var homes: List<AvailableHomesActivity.Home>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

     private val selectedHomes = mutableListOf<AvailableHomesActivity.Home>()
    fun getSelectedHomes(): List<AvailableHomesActivity.Home> {
        return homes.filter { selectedHomes.contains(it)

        }
    }
   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val addressTextView: TextView = itemView.findViewById(R.id.home_address)
        private val priceTextView: TextView = itemView.findViewById(R.id.home_price)
        private val imageView: ImageView = itemView.findViewById(R.id.home_image)
        private val checkBox: CheckBox = itemView.findViewById(R.id.home_checkbox)

        fun bind(home: AvailableHomesActivity.Home) {
            addressTextView.text = home.address
            priceTextView.text = "$ ${home.price}"
            imageView.setImageResource(home.imageResourceId)

            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = selectedHomes.contains(home)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedHomes.add(home)
                } else {
                    selectedHomes.remove(home)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val home = homes[position]
        holder.bind(home)
    }

    override fun getItemCount() = homes.size

    fun updateData(newHomes: List<AvailableHomesActivity.Home>) {
        homes = newHomes
        notifyDataSetChanged()
    }

    fun isAtLeastOneCheckBoxChecked(): Boolean {
        return selectedHomes.isNotEmpty()
    }



}
