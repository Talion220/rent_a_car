package com.bignerdranch.android.lab5

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var calculateButton: Button
    private lateinit var countOfHoursEditText: EditText
    private lateinit var carTypeRadioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countOfHoursEditText = findViewById(R.id.count_of_hours_edit_text)
        carTypeRadioGroup = findViewById(R.id.car_type_radio_group)
        calculateButton = findViewById(R.id.calculate_button)

        calculateButton.setOnClickListener { view ->
            val hoursText = countOfHoursEditText.text.toString()
            val carTypeCheckedId = carTypeRadioGroup.checkedRadioButtonId

            if (hoursText.isEmpty() || carTypeCheckedId == -1) {
                Toast.makeText(
                    applicationContext,
                    "Please enter hours and select a car type.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("hours", hoursText)
            intent.putExtra("carType", carTypeCheckedId)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions.makeSceneTransitionAnimation(this, view, "transition_name")
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }


        }
    }
}