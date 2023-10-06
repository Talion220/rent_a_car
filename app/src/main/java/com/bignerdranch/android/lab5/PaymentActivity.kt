package com.bignerdranch.android.lab5

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class PaymentActivity : AppCompatActivity() {
    private lateinit var payButton: Button
    private lateinit var costTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        payButton = findViewById(R.id.pay_button)
        costTextView = findViewById(R.id.cost_text_view)
        val hoursText = intent.getStringExtra("hours")
        val carTypeCheckedId = intent.getIntExtra("carType", -1)
        if (hoursText != null && carTypeCheckedId != -1) {
            val hours = hoursText.toFloat()
            val rate = when (carTypeCheckedId) {
                R.id.car_radio -> 2000.00f
                R.id.minivan_radio -> 2500.00f
                R.id.bus_radio -> 3500.00f
                else -> 0.00f
            }
            val cost = hours * rate
            val costString = getString(R.string.cost, cost)
            costTextView.text = costString
        }
        payButton.setOnClickListener{view->
            val intent = Intent(this, MainActivity::class.java)
            // Добавляем анимацию при переходе (необязательно)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions.makeSceneTransitionAnimation(this, view, "transition_name")
                startActivity(intent, options.toBundle())
                Toast.makeText(this, "the operation was successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "the operation was successful", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
    }
}