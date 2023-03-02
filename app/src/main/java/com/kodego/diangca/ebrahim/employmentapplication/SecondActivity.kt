package com.kodego.diangca.ebrahim.employmentapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.google.android.material.internal.CheckableGroup
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityMainBinding
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun onCheckbox(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.citizenYes -> {
                    if (checked) {
                        binding.citizenYes.isChecked = true
                        binding.citizenNo.isChecked = false
                    }else{
                        binding.citizenYes.isChecked = true
                        binding.citizenNo.isChecked = false
                    }
                }
                R.id.citizenNo -> {
                    if (checked) {
                        binding.citizenNo.isChecked = true
                        binding.citizenYes.isChecked = false
                    }else{
                        binding.citizenYes.isChecked = true
                        binding.citizenNo.isChecked = false
                    }
                }

                R.id.felonyYes -> {
                    if (checked) {
                        binding.felonyYes.isChecked = true
                        binding.felonyNo.isChecked = false
                    }else{
                        binding.felonyYes.isChecked = true
                        binding.felonyNo.isChecked = false
                    }
                }
                R.id.felonyNo -> {
                    if (checked) {
                        binding.felonyNo.isChecked = true
                        binding.felonyYes.isChecked = false
                    }else{
                        binding.felonyNo.isChecked = true
                        binding.felonyYes.isChecked = false
                    }
                }

                R.id.adikYes -> {
                    if (checked) {
                        binding.adikYes.isChecked = true
                        binding.adikNo.isChecked = false
                    }else{
                        binding.adikYes.isChecked = true
                        binding.adikNo.isChecked = false
                    }
                }

                R.id.adikNo -> {
                    if (checked) {
                        binding.adikNo.isChecked = true
                        binding.adikYes.isChecked = false
                    }else{
                        binding.adikNo.isChecked = true
                        binding.adikYes.isChecked = false
                    }
                }
                // TODO: Veggie sandwich
            }
        }

    }

}