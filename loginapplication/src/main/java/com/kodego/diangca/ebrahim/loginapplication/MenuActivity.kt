package com.kodego.diangca.ebrahim.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.diangca.ebrahim.loginapplication.databinding.ActivityMenuBinding
import java.text.SimpleDateFormat
import java.util.*

class MenuActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            btnLoginClickListener()
        }
        binding.btnRegister.setOnClickListener {
            btnRegisterClickListener()
        }

    }

    private fun btnRegisterClickListener() {
        var nextForm = Intent(this, RegisterActivity::class.java)
        startActivity(Intent(nextForm))
        finish()
    }

    private fun btnLoginClickListener() {

        var nextForm = Intent(this, LoginActivity::class.java)
        var bundle = Bundle()
        bundle.putString(
            "dateAvailable",SimpleDateFormat("yyyy-MM-d").format(Date())
        )
        nextForm.putExtras(bundle)

        nextForm.putExtra("something", SimpleDateFormat("yyyy-MM-d").format(Date()))

        startActivity(Intent(nextForm))

        finish()

    }
}