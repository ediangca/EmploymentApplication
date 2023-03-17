package com.kodego.diangca.ebrahim.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.loginapplication.databinding.ActivityMenuBinding
import java.text.SimpleDateFormat
import java.util.*

class MenuActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMenuBinding

    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data

        Log.d("FROM REGISTER", data!!.getStringExtra("username").toString())
        Snackbar.make(
            binding.root,
            "Hi  ${data!!.getStringExtra("firstname")}! \n Please wait for the confirmation of your Account",
            Snackbar.LENGTH_LONG
        ).show()
    }


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
        /*
        var nextForm = Intent(this, RegisterActivity::class.java)
        startActivity(Intent(nextForm))
        finish()
        */
        launchRegister.launch(Intent(this, RegisterActivity::class.java))

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