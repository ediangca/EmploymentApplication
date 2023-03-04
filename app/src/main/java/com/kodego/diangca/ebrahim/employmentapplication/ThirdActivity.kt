package com.kodego.diangca.ebrahim.employmentapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityMainBinding
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}