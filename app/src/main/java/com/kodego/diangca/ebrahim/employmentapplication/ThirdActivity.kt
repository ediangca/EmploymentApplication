package com.kodego.diangca.ebrahim.employmentapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.diangca.ebrahim.employmentapplication.adapter.EducationAdapter
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityThirdBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import com.kodego.diangca.ebrahim.employmentapplication.model.Education


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

    private lateinit var educationAdapter: EducationAdapter


    fun init() {
        educationList.add(Education("Shool1", "Panabo", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool2", "Tagum", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool3", "Davao", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool4", "Davao", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool5", "Digos", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool6", "Cagayan", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool7", "Comval", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool9", "Kidapawan", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool8", "Mati", 2000, "PILOT", "JET PLANE"))
        educationList.add(Education("Shool9", "Marawi", 2000, "PILOT", "JET PLANE"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        educationListAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, educationStringList)

        // DataBind ListView with items from ArrayAdapter
        binding.educationList.adapter = educationListAdapter

//        init()
        educationAdapter = EducationAdapter(educationList)


        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        binding.list.adapter = educationAdapter
//        binding.educationList.performItemClick(binding.educationList.selectedView, 0, 0);

        binding.btnEducationAdd.setOnClickListener {
            btnEducationAddOnClickListener(it)
        }

        binding.btnEducationEdit.setOnClickListener {
            btnEducationEditOnClickListener(it)
        }

        binding.btnEducationRemove.setOnClickListener {
            btnEducationRemoveOnClickListener(it)
        }

        binding.educationList.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // This is your listview's selected item
                selectItemPosition = position
                binding.educationList.setSelection(position)
                /*Toast.makeText(
                    applicationContext,
                    "ID: $id \n Position: $position",
                    Toast.LENGTH_SHORT
                ).show()*/
            }


        binding.btnNext.setOnClickListener {
            btnNextOnClickListener()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun btnEducationAddOnClickListener(view: View) {
        if (educationStringList.size==3) {
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
                    .isEmpty()
            ) 0 else binding.yearEnded.text.toString().toInt()
            degreeReceived = binding.degreeReceived.text.toString()
            major = binding.major.text.toString()
            educationList.add(Education(school, address, yearAttended, degreeReceived, major))
            educationStringList.add(school.plus(" - $degreeReceived) ($yearAttended)"))
            Toast.makeText(
                applicationContext,
                "Data has been Successfully Added!",
                Toast.LENGTH_SHORT
            ).show()
            clearFields(this.currentFocus!!)
            educationListAdapter.notifyDataSetChanged();
            educationAdapter.notifyDataSetChanged()

        }
    }

    private fun btnEducationEditOnClickListener(view: View) {
        if (selectItemPosition < 0) {
            Toast.makeText(
                applicationContext,
                "Please select a data to edit.",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else {
            if (binding.btnEducationEdit.text.toString().equals("Edit", true)) {
                binding.schoolName.setText(educationList[selectItemPosition].school)
                binding.address.setText(educationList[selectItemPosition].address)
                binding.yearEnded.setText(educationList[selectItemPosition].yearAttended.toString())
                binding.degreeReceived.setText(educationList[selectItemPosition].degreeReceived)
                binding.major.setText(educationList[selectItemPosition].major)

                binding.btnEducationAdd.visibility = View.GONE
                binding.btnEducationEdit.text = "Update"
                binding.btnEducationRemove.text = "Cancel"
            } else {
                educationList[selectItemPosition].school = binding.schoolName.text.toString()
                educationList[selectItemPosition].address = binding.address.text.toString()
                educationList[selectItemPosition].yearAttended =
                    if (binding.yearEnded.text.toString()
                            .isNullOrEmpty()
                    ) 0 else binding.yearEnded.text.toString().toInt()
                educationList[selectItemPosition].degreeReceived =
                    binding.degreeReceived.text.toString()
                educationList[selectItemPosition].major = binding.major.text.toString()

                Toast.makeText(
                    applicationContext,
                    "Data has been Successfully Updated!",
                    Toast.LENGTH_SHORT
                ).show()
                clearFields(this.currentFocus!!)
            }

        }
    }

    private fun btnEducationRemoveOnClickListener(view: View) {
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
            if (binding.btnEducationRemove.text.toString().equals("Remove", true)) {
                /*Toast.makeText(
                applicationContext,
                "Selected Position is greater than -1",
                Toast.LENGTH_SHORT
            ).show()*/
                var alertDialogBuilder = AlertDialog.Builder(this@ThirdActivity)
                alertDialogBuilder.setTitle("Delete?")
                alertDialogBuilder.setMessage("Are you sure you want to delete ${educationList[selectItemPosition].school}")
                alertDialogBuilder.setNegativeButton("Cancel", null)
                alertDialogBuilder.setPositiveButton("Yes", object : OnClickListener {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onClick(dialog: DialogInterface?, position: Int) {
                        educationList.removeAt(selectItemPosition)
                        educationStringList.removeAt(selectItemPosition)

                        educationListAdapter.notifyDataSetChanged()
                        binding.educationList.invalidateViews();

                        educationAdapter.notifyDataSetChanged()
                        binding.list.invalidateItemDecorations()
                        Toast.makeText(
                            applicationContext,
                            "Data has been Successfully Removed!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                alertDialogBuilder.show()
                clearFields(this.currentFocus!!)
            } else {
                clearFields(this.currentFocus!!)
            }
        }

    }

    private fun clearFields(view: View) {
        binding.schoolName.text!!.clear()
        binding.address.text!!.clear()
        binding.yearEnded.text!!.clear()
        binding.degreeReceived.text!!.clear()
        binding.major.text!!.clear()
        binding.educationList.clearChoices()
        selectItemPosition = -1
        binding.btnEducationEdit.text = "Edit"
        binding.btnEducationRemove.text = "Remove"
        binding.btnEducationAdd.visibility = View.VISIBLE
        if (educationStringList.size > 0) {
            binding.linearEducationalBg.visibility = View.VISIBLE
        } else {
            binding.linearEducationalBg.visibility = View.GONE
        }
        binding.educationList.setSelection(-1)
        binding.educationList.clearChoices()
        binding.educationList.requestLayout()
        binding.educationList.requestFocus()
        binding.educationList.invalidateViews();

        // on below line checking if view is not null.
        if (view!=null) {
            // on below line we are creating a variable
            // for input manager and initializing it.
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            // on below line hiding our keyboard.
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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
            if (binding.yearEnded.text.toString()
                    .toInt() <= 0 || binding.yearEnded.text.toString()
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