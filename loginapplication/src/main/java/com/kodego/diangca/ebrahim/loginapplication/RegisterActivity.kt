package com.kodego.diangca.ebrahim.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.kodego.diangca.ebrahim.loginapplication.databinding.ActivityRegisterBinding
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {
            btnRegisterClickListener()
        }
        binding.btnBack.setOnClickListener {
            btnBackClickListener()
        }
    }

    private fun btnBackClickListener() {
        var nextForm = Intent(this, MenuActivity::class.java)
        startActivity(Intent(nextForm))
        finish()
    }

    private fun btnRegisterClickListener() {

        val intent = Intent()
        intent.putExtra("username", binding.username.text.toString())
        setResult(1, intent)
        finish()

    }


}