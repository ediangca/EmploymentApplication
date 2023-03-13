package com.kodego.diangca.ebrahim.loginapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.loginapplication.databinding.ActivityLoginBinding
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("SimpleDateFormat")
    private var dateFormat = SimpleDateFormat("yyyy-MM-d")

    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data

        Log.d("FROM REGISTER", data!!.getStringExtra("username").toString())
        Snackbar.make(
            binding.root,
            "Registered ${data!!.getStringExtra("username")}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnForgotPass.setOnClickListener {
            btnForgotPassClickListener()
        }
        binding.btnSubmit.setOnClickListener {
            btnSubmitClickListener()
        }
        binding.btnRegister.setOnClickListener {
            btnRegisterClickListener()
        }


    }

    private fun btnRegisterClickListener() {
        launchRegister.launch(Intent(this, RegisterActivity::class.java))
    }

    private fun btnSubmitClickListener() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Either Username or Password is Invalid. \n Please try again!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            var nextForm = Intent(this, MainActivity::class.java)
            var bundle = Bundle()
            bundle.putString("username", binding.username.text.toString())
            bundle.putString("password", binding.password.text.toString())
            nextForm.putExtras(bundle)

            nextForm.putExtra("dateLog", dateFormat.format(Date()))

            startActivity(Intent(nextForm))

            finish()
        }
    }

    private fun btnForgotPassClickListener() {
        var nextForm = Intent(this, ForgotPassActivity::class.java)
        nextForm.putExtra("something", dateFormat.format(Date()))
        startActivity(Intent(nextForm))
        finish()
    }
}