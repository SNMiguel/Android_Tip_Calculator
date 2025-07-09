package com.example.android_tip_calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_tip_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set button click behavior
        binding.btnCalculate.setOnClickListener {
            val bill = binding.etBillAmount.text.toString().toDoubleOrNull()
            val people = binding.etNumPeople.text.toString().toIntOrNull()

            // Validate input
            if (bill == null || bill <= 0) {
                Toast.makeText(this, "Enter valid bill amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (people == null || people <= 0) {
                Toast.makeText(this, "Enter valid number of people", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Get tip percent from selected RadioButton
            val tipPercent = when (binding.rgTipOptions.checkedRadioButtonId) {
                R.id.rb15 -> 0.15
                R.id.rb18 -> 0.18
                R.id.rb20 -> 0.20
                else -> 0.0
            }

            // Calculate tip and total
            val tip = bill * tipPercent
            val total = bill + tip

            // Calculate per-person values
            val tipPerPerson = tip / people
            val totalPerPerson = total / people

            // Update UI
            binding.tvTipResult.text = "Tip per Person: $${"%.2f".format(tipPerPerson)}"
            binding.tvTotalResult.text = "Total per Person: $${"%.2f".format(totalPerPerson)}"
        }
    }
}
