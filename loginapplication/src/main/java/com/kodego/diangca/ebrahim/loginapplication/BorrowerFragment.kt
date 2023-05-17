package com.kodego.diangca.ebrahim.loginapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.loginapplication.adapter.BookAdapter
import com.kodego.diangca.ebrahim.loginapplication.adapter.BorrowerAdapter
import com.kodego.diangca.ebrahim.loginapplication.databinding.FragmentBorrowerBinding
import com.kodego.diangca.ebrahim.loginapplication.model.Book
import com.kodego.diangca.ebrahim.loginapplication.model.Borrower

class BorrowerFragment(var mainActivity: MainActivity) : Fragment() {

    private var _binding: FragmentBorrowerBinding? = null
    private val binding get() = _binding!!


    var borrowers: ArrayList<Borrower> = ArrayList()
    private lateinit var borrowerAdapter: BorrowerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ATTACH_BORROWER_FRAGMENT", "BORROWER_FRAGMENT_ATTACHED")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ATTACH_BORROWER_RESUME", "BORROWER_FRAGMENT_RESUMED")
        borrowers = mainActivity.borrowers
    }

    override fun onPause() {
        super.onPause()
        Log.d("ATTACH_BORROWER_PAUSE", "BORROWER_FRAGMENT_PAUSED")
        mainActivity.borrowers = borrowers
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBorrowerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initComponent() {

        borrowers = mainActivity.borrowers

//        restoreBook()

        Log.d("ATTACH_BORROWER_INIT", "BORROWER_FRAGMENT_INIT >>> ${borrowers.size}")

        borrowerAdapter = BorrowerAdapter(mainActivity, borrowers)

        binding.list.layoutManager = LinearLayoutManager(mainActivity)
        binding.list.adapter = borrowerAdapter

        binding.list.adapter!!.notifyDataSetChanged()
        borrowerAdapter!!.notifyDataSetChanged()


        binding.btnAdd.setOnClickListener {
            btnAddOnClickListener()
        }

        binding.searchBook.setOnKeyListener { v, keyCode, event ->
            Log.d("LIST_BOOK_SELECTED_KEY_LISTENER", "${binding.searchBook.text}")
            searchBookOnKeyListener(binding.searchBook.text.toString())
        }
    }


    private fun btnAddOnClickListener() {
        val firstName = binding.firstName.text
        val lastName = binding.lastName.text
        if (firstName.isNullOrEmpty() || lastName.isNullOrEmpty()) {
            Snackbar.make(binding.root, "Please check empty fields!", Snackbar.LENGTH_SHORT).show()
            return
        } else {
            borrowers.add(Borrower(firstName.toString(), lastName.toString()))
            Snackbar.make(binding.root, "Book has been successfully added !", Snackbar.LENGTH_SHORT)
                .show()
            clearFields()
        }
    }

    public fun clearFields() {
        binding.firstName.text = null
        binding.lastName.text = null
        binding.searchBook.text = null

        binding.list.adapter!!.notifyDataSetChanged()
        borrowerAdapter!!.notifyDataSetChanged()
        binding.list.requestFocus()
    }


    private fun searchBookOnKeyListener(filter: String): Boolean {

        var borrowerList = ArrayList<Borrower>()

        for (borrower in borrowers) {
            if (borrower.firstName!!.contains(filter, true) || borrower.lastName!!.contains(
                    filter,
                    true
                )
            ) {
                borrowerList.add(borrower)
            }
        }

        borrowerAdapter = BorrowerAdapter(mainActivity, borrowerList)

        binding.list.layoutManager = LinearLayoutManager(mainActivity)
        binding.list.adapter = borrowerAdapter

        binding.list.adapter!!.notifyDataSetChanged()
        borrowerAdapter!!.notifyDataSetChanged()

        return false
    }

}