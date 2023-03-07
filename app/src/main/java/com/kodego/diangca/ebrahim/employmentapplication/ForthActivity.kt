package com.kodego.diangca.ebrahim.employmentapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityForthBinding

class ForthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {

            var nextForm = Intent(this, FifthActivity::class.java)

            startActivity(Intent(nextForm))

            finish()
        }

    }

    fun onCheckbox(view: View) {


        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.contactYes -> {
                    if (checked) {
                        binding.contactNo.isChecked = true
                        binding.contactYes.isChecked = false
                    } else {
                        binding.contactYes.isChecked = true
                        binding.contactNo.isChecked = false
                    }
                }
                R.id.contactNo -> {
                    if (checked) {
                        binding.contactYes.isChecked = false
                        binding.contactNo.isChecked = true
                    } else {
                        binding.contactYes.isChecked = true
                        binding.contactNo.isChecked = false
                    }
                }
            }
        }
    }
}