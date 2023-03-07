package com.kodego.diangca.ebrahim.employmentapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityThirdBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var school: String
    private lateinit var address: String
    private var yearAttended by Delegates.notNull<Int>()
    private lateinit var degreeReceived: String
    private lateinit var major: String
    private lateinit var educationListAdapter: ArrayAdapter<*>
    private var educationList = ArrayList<Education>()
    private var educationStringList = ArrayList<String>()
    private var selectItemPosition: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        educationListAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, educationStringList)

        // DataBind ListView with items from ArrayAdapter
        binding.educationList.adapter = educationListAdapter
        binding.educationList.performItemClick(binding.educationList.selectedView, 0, 0);

        binding.btnEducationAdd.setOnClickListener {
            btnEducationAddOnClickListener()
        }

        binding.btnEducationEdit.setOnClickListener {
            btnEducationEditOnClickListener()
        }

        binding.btnEducationRemove.setOnClickListener {
            btnEducationRemoveOnClickListener()
        }

        binding.educationList.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // This is your listview's selected item
                selectItemPosition = position
                binding.educationList.setSelection(position)
                Toast.makeText(
                    applicationContext,
                    "ID: $id \n Position: $position",
                    Toast.LENGTH_SHORT
                ).show()
            }


        binding.btnNext.setOnClickListener {
            btnNextOnClickListener()
        }
    }

    private fun btnEducationAddOnClickListener() {
        if(educationStringList.size == 3){
            Toast.makeText(
                applicationContext,
                "Maximum of 3 Entry only.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (checkEducationField()) {
            school = binding.schoolName.text.toString()
            address = binding.address.text.toString()
            yearAttended = if (binding.yearEnded.text.toString()
                    .isNullOrEmpty()
            ) 0 else binding.yearEnded.text.toString().toInt()
            degreeReceived = binding.degreeReceived.text.toString()
            major = binding.major.text.toString()
            var education = Education(school, address, yearAttended, degreeReceived, major)
            educationList.add(education)
            educationStringList.add(school.plus("($degreeReceived)"))
            clearFields()
            educationListAdapter.notifyDataSetChanged();

        }
    }

    private fun btnEducationEditOnClickListener() {

    }

    private fun btnEducationRemoveOnClickListener() {
/*        Toast.makeText(
            applicationContext,
            "Selected Position $selectItemPosition",
            Toast.LENGTH_SHORT
        ).show()*/
        if (selectItemPosition < 0) {
            Toast.makeText(
                applicationContext,
                "Please select a data to remove.",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else {
            Toast.makeText(
                applicationContext,
                "Selected Position is greater than -1",
                Toast.LENGTH_SHORT
            ).show()
            var alertDialogBuilder = AlertDialog.Builder(this@ThirdActivity)
            alertDialogBuilder.setTitle("Delete?")
            alertDialogBuilder.setMessage("Are you sure you want to delete ${educationList[selectItemPosition].school}")
            alertDialogBuilder.setNegativeButton("Cancel", null)
            alertDialogBuilder.setPositiveButton("Yes", object : OnClickListener {
                override fun onClick(dialog: DialogInterface?, position: Int) {
                    educationList.removeAt(selectItemPosition)
                    educationStringList.removeAt(selectItemPosition)
                    educationListAdapter.notifyDataSetChanged()
                    binding.educationList.invalidateViews();
                    clearFields()
                }
            })
            alertDialogBuilder.show()
        }
    }

    private fun clearFields() {
        binding.schoolName.text!!.clear()
        binding.address.text!!.clear()
        binding.yearEnded.text!!.clear()
        binding.degreeReceived.text!!.clear()
        binding.major.text!!.clear()
        if (educationStringList.size > 0) {
            binding.linearEducationalBg.visibility = View.VISIBLE
        } else {
            binding.linearEducationalBg.visibility = View.GONE
        }
    }

    private fun checkEducationField(): Boolean {
        var valid: Boolean = true
        var prompt = "Please check the following fields: \n"

        if (binding.schoolName.text.toString().isNullOrEmpty()) {
            prompt += "* School.\n"
            valid = false
        }
        if (binding.address.text.toString().isNullOrEmpty()) {
            prompt += "* School.\n"
            valid = false
        }
        if (binding.yearEnded.text.toString().isNullOrEmpty()) {
            prompt += "* Year Attended.\n"
            valid = false
        }
        if (!binding.yearEnded.text.toString().isNullOrEmpty()) {
            if (binding.yearEnded.text.toString().toInt() <= 0 || binding.yearEnded.text.toString()
                    .toInt() > SimpleDateFormat("yyyy").format(Date()).toInt()
            ) {
                prompt += "* Year Attended must not less than or equal to zero and greater than current year (${
                    SimpleDateFormat(
                        "yyyy"
                    ).format(Date()).toInt()
                }).\n"
                valid = false
            }
        }
        if (binding.degreeReceived.text.isNullOrEmpty()) {
            prompt += "* Year Attended.\n"
            valid = false
        }
        if (binding.major.text.toString().isNullOrEmpty()) {
            prompt += "* Major.\n"
            valid = false
        }
        if (!valid) {
            Toast.makeText(this, prompt, Toast.LENGTH_SHORT).show()
        }

        return valid

    }

    private fun btnNextOnClickListener() {

        var nextForm = Intent(this, ForthActivity::class.java)
        startActivity(Intent(nextForm))
        finish()
    }

}