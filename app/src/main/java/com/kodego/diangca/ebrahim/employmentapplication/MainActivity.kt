package com.kodego.diangca.ebrahim.employmentapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var positionApply: String
    private var desiredSalary: Double = 0.0
    private var dateAvailable: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProceed1.setOnClickListener {
                btnProceed1OnClickListener()

        }
        binding.positionList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                positionListOnItemSelected(parent, view, position, id)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }

    private fun positionListOnItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(position == 0){
            binding.positionApply.text = null
            Toast.makeText(this, "Please select a position first.", Toast.LENGTH_SHORT).show()
            return
        }
        binding.positionApply.setText(binding.positionList.selectedItem.toString())
//        Toast.makeText(this, "Selected Position : ${parent!!.selectedItemId} - ${binding.positionList.selectedItem.toString()}", Toast.LENGTH_SHORT).show()
    }

    private fun btnProceed1OnClickListener() {

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
        } else {

            var nextForm = Intent(this, SecondActivity::class.java)
            var bundle = Bundle()
            bundle.putString("positionApplied", positionApply)
            bundle.putDouble("desiredSalary", desiredSalary)
            bundle.putString(
                "dateAvailable",
                SimpleDateFormat("yyyy-MM-d").format(dateAvailable)
            )
            nextForm.putExtras(bundle)

            nextForm.putExtra("something", "Extra")

            startActivity(Intent(nextForm))

            finish()

        }
    }


}