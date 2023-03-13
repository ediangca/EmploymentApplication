package com.kodego.diangca.ebrahim.loginapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.kodego.diangca.ebrahim.loginapplication.databinding.StudentItemBinding
import com.kodego.diangca.ebrahim.loginapplication.model.Student

class StudentAdapter(var students: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {


    override fun getItemCount(): Int {
        return students.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.StudentViewHolder {
        val itemBinding =
            StudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {
        holder.bindStudent(students[position])
    }

    inner class StudentViewHolder(private val itemBinding: StudentItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var student = Student()
        fun bindStudent(student: Student) {
            this.student = student
            itemBinding.studentName.text = "${student.lastName}, ${student.firstName}"
        }

        override fun onClick(v: View?) {
        }
    }
}