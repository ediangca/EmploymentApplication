package com.kodego.diangca.ebrahim.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.diangca.ebrahim.loginapplication.adapter.StudentAdapter
import com.kodego.diangca.ebrahim.loginapplication.databinding.ActivityMainBinding
import com.kodego.diangca.ebrahim.loginapplication.model.Student
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private var students: ArrayList<Student> = ArrayList()

    fun init() {
        students.add(Student("Sacar", "Diangca"))
        students.add(Student("Naima", "Diangca"))
        students.add(Student("Jaharah", "Diangca"))
        students.add(Student("Asliah", "Diangca"))
        students.add(Student("Ebrahin", "Diangca"))
        students.add(Student("Rose Marie", "Diangca"))
        students.add(Student("Ebmarie", "Diangca"))
        students.add(Student("Mohammad Rafi", "Diangca"))
        students.add(Student("Farhana", "Diangca"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            btnLogoutClickListener()
        }
        init()
        studentAdapter = StudentAdapter(students)


        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        binding.list.adapter =studentAdapter
    }

    private fun btnLogoutClickListener() {
        var nextForm = Intent(this, MenuActivity::class.java)
        startActivity(Intent(nextForm))
        finish()
    }
}