package com.kodego.diangca.ebrahim.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.loginapplication.adapter.StudentAdapter
import com.kodego.diangca.ebrahim.loginapplication.databinding.ActivityMainBinding
import com.kodego.diangca.ebrahim.loginapplication.model.Student
import kotlin.collections.ArrayList

class   MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private var students: ArrayList<Student> = ArrayList()

    fun init() {
        students.add(Student("Sacar", "Diangca", R.drawable.profile_icon))
        students.add(Student("Naima", "Diangca", R.drawable.profile_icon))
        students.add(Student("Jaharah", "Diangca", R.drawable.profile_3))
        students.add(Student("Asliah", "Diangca", R.drawable.profile_2))
        students.add(Student("Ebrahin", "Diangca", R.drawable.profile_1))
        students.add(Student("Rose Marie", "Diangca", R.drawable.profile_2))
//        students.add(Student("Ebmarie", "Diangca", R.drawable.profile_4))
        students.add(Student("Mohammad Rafi", "Diangca", R.drawable.profile_5))
        students.add(Student("Farhana", "Diangca", R.drawable.profile_4))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            btnLogoutClickListener()
        }
        binding.btnAdd.setOnClickListener {
            btnAddClickListener(this.currentFocus!!)
        }
        init()
        studentAdapter = StudentAdapter(this, students)

        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        binding.list.adapter = studentAdapter
    }

    private fun btnAddClickListener(view: View?) {
        var firstName = binding.firstName.text.toString()
        var lastName = binding.lastName.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty()) {
            Snackbar.make(binding.root, "Please check empty fields.", Snackbar.LENGTH_SHORT).show()
            return
        }
        students.add(Student(firstName, lastName, R.drawable.profile_icon))
        studentAdapter.notifyDataSetChanged()
        Snackbar.make(binding.root, "Data has been successfully added.", Snackbar.LENGTH_SHORT)
            .show()
        binding.firstName.setText("")
        binding.lastName.setText("")

        // on below line checking if view is not null.
        if (view!=null) {
            // on below line we are creating a variable
            // for input manager and initializing it.
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            // on below line hiding our keyboard.
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            binding.list.requestFocus()
        }

    }

    private fun btnLogoutClickListener() {
        var nextForm = Intent(this, MenuActivity::class.java)
        startActivity(Intent(nextForm))
        finish()
    }
}