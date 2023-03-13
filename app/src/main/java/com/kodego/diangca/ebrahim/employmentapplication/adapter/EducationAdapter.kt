package com.kodego.diangca.ebrahim.employmentapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.diangca.ebrahim.employmentapplication.databinding.EducationItemBinding
import com.kodego.diangca.ebrahim.employmentapplication.model.Education

class EducationAdapter(var educations: ArrayList<Education>) :
    RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {


    override fun getItemCount(): Int {
        return educations.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationAdapter.EducationViewHolder {
        val itemBinding =
            EducationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EducationViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EducationAdapter.EducationViewHolder, position: Int) {
        holder.bindStudent(educations[position])
    }

    inner class EducationViewHolder(private val itemBinding: EducationItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var education = Education()
        fun bindStudent(education: Education) {
            this.education = education
            itemBinding.schoolName.text = education.school
            itemBinding.yearAttended.text = education.yearAttended.toString()
            itemBinding.degreeReceived.text = education.degreeReceived
            itemBinding.major.text = education.major
        }

        override fun onClick(v: View?) {
        }
    }
}