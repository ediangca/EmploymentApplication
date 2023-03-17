package com.kodego.diangca.ebrahim.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.diangca.ebrahim.fragments.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}