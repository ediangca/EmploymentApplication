package com.kodego.diangca.ebrahim.employmentapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityMainBinding
import java.util.Date
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var positionApply: String
    private var desiredSalary by Delegates.notNull<Double>()
    private var dateAvailable: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProceed1.setOnClickListener {

            positionApply = try {
                binding.positionApply.text.toString()
            } catch (e: Exception) {
                ""
            }
            desiredSalary = try {
                binding.desiredSalary.text.toString().toDouble()
            } catch (e: Exception) {
                0.0
            }
            dateAvailable = try {
                Date(binding.dateAvailable.text.toString())
            } catch (e: Exception) {
                null
            }

/*
            Snackbar.make(binding.root,"PROCEED APPLICATION  Position Applying For: $positionApply Desired Salary: $desiredSalary Date Available: $dateAvailable",Snackbar.LENGTH_LONG).show()
            Toast.makeText(this, "PROCEED APPLICATION  Position Applying For: $positionApply Desired Salary: $desiredSalary Date Available: $dateAvailable", Toast.LENGTH_SHORT).show()
*/
            if (positionApply.isNullOrEmpty() or (desiredSalary == 0.0) or (dateAvailable == null)) {
                Toast.makeText(this, "Please indicate field.", Toast.LENGTH_SHORT).show()
            }else{
                var nextForm = Intent(this, SecondActivity::class.java)
                startActivity(nextForm)
            }


        }


    }


}