package com.kodego.diangca.ebrahim.employmentapplication

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.kodego.diangca.ebrahim.employmentapplication.databinding.ActivityFifthBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding
    private lateinit var name: String
    private lateinit var title: String
    private lateinit var company: String
    private lateinit var phone: String
    private lateinit var referenceListAdapter: ArrayAdapter<*>
    private var referenceList = ArrayList<References>(3)
    private var referenceStringList = ArrayList<String>(3)
    private var selectItemPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        referenceListAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, referenceStringList)

        // DataBind ListView with items from ArrayAdapter
        binding.referenceList.adapter = referenceListAdapter

        binding.btnReferenceAdd.setOnClickListener {
            btnReferenceAddOnClickListener()
        }

        binding.btnReferenceEdit.setOnClickListener {
            btnReferenceEditOnClickListener()
        }

        binding.btnReferenceRemove.setOnClickListener {
            btnReferenceRemoveOnClickListener()
        }

        binding.referenceList.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // This is your listview's selected item
                selectItemPosition = position
                binding.referenceList.setSelection(position)
                /*Toast.makeText(
                    applicationContext,
                    "ID: $id \n Position: $position",
                    Toast.LENGTH_SHORT
                ).show()*/
            }

    }

    private fun btnReferenceAddOnClickListener() {
        if (referenceStringList.size==3) {
            Toast.makeText(
                applicationContext,
                "Maximum of 3 Entry only.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (checkEducationField()) {
            name = binding.referenceName.text.toString()
            company = binding.referenceCompany.text.toString()
            title = binding.referencesTitle.text.toString()
            phone = binding.referencePhone.text.toString()
            var references = References(name, title, company, phone)
            referenceList.add(references)
            referenceStringList.add(name.plus(" - $title) ($company)"))
            Toast.makeText(
                applicationContext,
                "Data has been Successfully Added!",
                Toast.LENGTH_SHORT
            ).show()
            clearFields()
            referenceListAdapter.notifyDataSetChanged();

        }
    }

    private fun btnReferenceEditOnClickListener() {
        if (selectItemPosition < 0) {
            Toast.makeText(
                applicationContext,
                "Please select a data to edit.",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else {
            if (binding.btnReferenceEdit.text.toString().equals("Edit", true)) {
                binding.referenceName.setText(referenceList[selectItemPosition].name)
                binding.referencesTitle.setText(referenceList[selectItemPosition].title)
                binding.referenceCompany.setText(referenceList[selectItemPosition].company)
                binding.referencePhone.setText(referenceList[selectItemPosition].phone)

                binding.btnReferenceAdd.visibility = View.GONE
                binding.btnReferenceEdit.text = "Update"
                binding.btnReferenceRemove.text = "Cancel"
            } else {
                referenceList[selectItemPosition].name = binding.referenceName.text.toString()
                referenceList[selectItemPosition].title = binding.referencesTitle.text.toString()
                referenceList[selectItemPosition].company =
                    binding.referenceCompany.text.toString()
                referenceList[selectItemPosition].phone = binding.referencePhone.text.toString()

                Toast.makeText(
                    applicationContext,
                    "Data has been Successfully Updated!",
                    Toast.LENGTH_SHORT
                ).show()
                clearFields()
            }

        }
    }

    private fun btnReferenceRemoveOnClickListener() {
        if (selectItemPosition < 0) {
            Toast.makeText(
                applicationContext,
                "Please select a data to remove.",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else {
            if (binding.btnReferenceRemove.text.toString().equals("Remove", true)) {
                /*Toast.makeText(
                applicationContext,
                "Selected Position is greater than -1",
                Toast.LENGTH_SHORT
            ).show()*/
                var alertDialogBuilder = AlertDialog.Builder(this@FifthActivity)
                alertDialogBuilder.setTitle("Delete?")
                alertDialogBuilder.setMessage("Are you sure you want to delete ${referenceList[selectItemPosition].name}")
                alertDialogBuilder.setNegativeButton("Cancel", null)
                alertDialogBuilder.setPositiveButton("Yes", object :
                    DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, position: Int) {
                        referenceList.removeAt(selectItemPosition)
                        referenceStringList.removeAt(selectItemPosition)
                        referenceListAdapter.notifyDataSetChanged()
                        binding.referenceList.invalidateViews();
                        Toast.makeText(
                            applicationContext,
                            "Data has been Successfully Removed!",
                            Toast.LENGTH_SHORT
                        ).show()
                        clearFields()
                    }
                })
                alertDialogBuilder.show()
            } else {
                clearFields()
            }
        }
    }

    private fun checkEducationField(): Boolean {
        var valid: Boolean = true
        var prompt = "Please check the following fields: \n"

        if (binding.referenceName.text.toString().isNullOrEmpty()) {
            prompt += "* Name.\n"
            valid = false
        }
        if (binding.referencesTitle.text.toString().isNullOrEmpty()) {
            prompt += "* Title.\n"
            valid = false
        }
        if (binding.referenceCompany.text.isNullOrEmpty()) {
            prompt += "* Company.\n"
            valid = false
        }
        if (binding.referencePhone.text.toString().isNullOrEmpty()) {
            prompt += "* Phone.\n"
            valid = false
        }
        if (!valid) {
            Toast.makeText(this, prompt, Toast.LENGTH_SHORT).show()
        }

        return valid

    }

    private fun clearFields() {
        binding.referenceName.text!!.clear()
        binding.referencesTitle.text!!.clear()
        binding.referenceCompany.text!!.clear()
        binding.referencePhone.text!!.clear()
        selectItemPosition = -1
        binding.btnReferenceEdit.text = "Edit"
        binding.btnReferenceRemove.text = "Remove"
        binding.btnReferenceAdd.visibility = View.VISIBLE
        if (referenceStringList.size > 0) {
            binding.linearReference.visibility = View.VISIBLE
        } else {
            binding.linearReference.visibility = View.GONE
        }
    }
}