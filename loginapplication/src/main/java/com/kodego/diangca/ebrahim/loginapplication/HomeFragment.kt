package com.kodego.diangca.ebrahim.loginapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.loginapplication.adapter.BookAdapter
import com.kodego.diangca.ebrahim.loginapplication.adapter.TransactionAdapter
import com.kodego.diangca.ebrahim.loginapplication.databinding.BookDirectoryDialogBinding
import com.kodego.diangca.ebrahim.loginapplication.databinding.FragmentHomeBinding
import com.kodego.diangca.ebrahim.loginapplication.model.Book
import com.kodego.diangca.ebrahim.loginapplication.model.Borrower
import com.kodego.diangca.ebrahim.loginapplication.model.Transaction
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment(var mainActivity: MainActivity) : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookDirectoryDialogBinding: BookDirectoryDialogBinding


    private lateinit var bookListAdapter: ArrayAdapter<*>
    private lateinit var bookAdapter: BookAdapter


    private var books = ArrayList<Book>()
    private var bookList = ArrayList<String>()
    private var selectedBookPosition = -1
//    private var resultBookList = ArrayList<Book>()

    private lateinit var book: Book
    private lateinit var borrower: Borrower
    private lateinit var transaction: Transaction
    private lateinit var adapterTransaction: TransactionAdapter
    private var transactions: ArrayList<Transaction> = ArrayList()
    private var simpleDateFormat = SimpleDateFormat("yyyy-MM-d")
    private var dateBorrowed = Date()
    private var dateReturn = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("HOME_FRAGMENT_TRANSACTIONS", "ON_ATTACH ${transactions.size}")
        Log.d("HOME_FRAGMENT_BOOKS", "ON_ATTACH ${books.size}")
    }

    override fun onResume() {
        super.onResume()

        transactions = mainActivity.transactions
        Log.d("HOME_FRAGMENT_INIT", "HOME_FRAGMENT_INIT ${transactions.size}")
        adapterTransaction = TransactionAdapter(mainActivity, transactions)
        binding.list.layoutManager = LinearLayoutManager(mainActivity.applicationContext)
        binding.list.adapter = adapterTransaction
        binding.list.adapter!!.notifyDataSetChanged()

        books = mainActivity.books
        restoreBook()
    }

    override fun onPause() {
        super.onPause()
        mainActivity.transactions = transactions
        Log.d("HOME_FRAGMENT", "ON_PAUSE ${transactions.size}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("HOME_FRAGMENT", "ON_DESTROY_VIEW ${transactions.size}")
    }


    private fun initComponent() {

        adapterTransaction = TransactionAdapter(mainActivity, transactions)

        binding.list.layoutManager = LinearLayoutManager(mainActivity.applicationContext)
        binding.list.adapter = adapterTransaction

        binding.dateBorrowed.setText(SimpleDateFormat("yyyy-MM-d").format(Date()).toString())
        binding.dateReturn.setText(SimpleDateFormat("yyyy-MM-d").format(Date()).toString())

        restoreBook()


        binding.btnBook.setOnClickListener {
            btnBookOnClickListener()
        }
        binding.btnBorrower.setOnClickListener {
            btnBorrowerOnClickListener()
        }
        binding.btnAdd.setOnClickListener {
            btnAddOnClickListener()
        }

        binding.btnNew.setOnClickListener {
            btnNewOnClickListener()
        }

        binding.btnBorrowed.setOnClickListener {

            datePickerDialog1()
            Log.d("DATE_PICKER_SELECTED", "$dateBorrowed")
        }
        binding.btnReturn.setOnClickListener {

            datePickerDialog2()
            Log.d("DATE_PICKER_SELECTED", "$dateReturn")

        }

    }

    private fun restoreBook() {
        bookList.clear()
        for (book in books){
            bookList.add(book.bookName.toString())
            println("BOOK_LIST ${book.bookName.toString()}")
        }
    }

    private fun btnNewOnClickListener() {
        if (binding.linearForm.visibility==View.GONE) {
            binding.linearForm.visibility = View.VISIBLE
            binding.btnNew.setImageResource(R.drawable.vector_cancel)
        } else {
            binding.linearForm.visibility = View.GONE
            binding.btnNew.setImageResource(R.drawable.vector_add)
            clearfields()
        }
    }

    private fun datePickerDialog1() {

        val getDate: Calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            mainActivity,
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selectDate = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                dateBorrowed = selectDate.time
                val date = simpleDateFormat.format(selectDate.time)
                binding.dateBorrowed.setText(date)
            },
            getDate.get(Calendar.YEAR),
            getDate.get(Calendar.MONTH),
            getDate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        datePicker.show()
    }


    private fun datePickerDialog2() {

        val getDate: Calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            mainActivity,
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selectDate = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                dateReturn = selectDate.time
                val date = simpleDateFormat.format(selectDate.time)
                binding.dateReturn.setText(date)
            },
            getDate.get(Calendar.YEAR),
            getDate.get(Calendar.MONTH),
            getDate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        datePicker.show()
    }

    private fun btnBookOnClickListener() {
        showBookDirectory()
    }

    private fun btnBorrowerOnClickListener() {

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun btnAddOnClickListener() {
        val booktext = binding.book.text.toString()
        val borrowertext = binding.borrower.text.toString()
        val dateBorrowedText = binding.dateBorrowed.text.toString()
        val dateReturnText = binding.dateReturn.text.toString()
        if (booktext.isNullOrEmpty() || borrowertext.isNullOrEmpty() || dateBorrowedText.isNullOrEmpty() || dateReturnText.isNullOrEmpty() ||
            dateBorrowedText.equals("date borrowed", true) || dateReturnText.equals(
                "date return",
                true
            )
        ) {
            Snackbar.make(binding.root, "Please check empty fields", Snackbar.LENGTH_SHORT).show()
            return
        } else {
            book = Book(booktext)
            borrower = Borrower(borrowertext, borrowertext)
            transaction = Transaction(book, borrower)
            transaction.dateBorrowed = dateBorrowed
            transaction.dateReturn = dateReturn
            transactions.add(transaction)

            clearfields()
        }
    }

    private fun clearfields() {

        binding.book.text = null
        binding.borrower.text = null
        binding.dateBorrowed.setText(SimpleDateFormat("yyyy-MM-d").format(Date()).toString())
        binding.dateReturn.setText(SimpleDateFormat("yyyy-MM-d").format(Date()).toString())

        binding.list.adapter!!.notifyDataSetChanged()
        binding.list.requestFocus()
    }

    private fun showBookDirectory() {

        //Inflate the dialog as custom view

        bookDirectoryDialogBinding = BookDirectoryDialogBinding.inflate(layoutInflater)

        bookListAdapter =
            ArrayAdapter(mainActivity, android.R.layout.simple_list_item_1,bookList)

        // DataBind ListView with items from ArrayAdapter
        bookDirectoryDialogBinding.listBook.adapter = bookListAdapter


        // DataBind RecycleView with items from ArrayAdapter
//        bookAdapter = BookAdapter(resultBookList)

        bookDirectoryDialogBinding.filter.setOnKeyListener { v, keyCode, event ->
            Log.d("LIST_BOOK_SELECTED_KEY_LISTENER", "${bookDirectoryDialogBinding.filter.text}")
            filterOnKeyListener(bookDirectoryDialogBinding.filter.text.toString())
        }
/*
        // For AdapterView onItemClickListener
        bookDirectoryDialogBinding.listBook.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // This is your listview's selected item
                Log.d("LIST_BOOK_SELECTED_LISTENER_1", bookDirectoryDialogBinding.listBook.selectedItem.toString())
            }

*/

        bookDirectoryDialogBinding.listBook.setOnItemClickListener { parent, view, position, id ->
            selectedBookPosition = position
            Log.d("LIST_BOOK_SELECTED_LISTENER_2", "$position -- ${bookList[position]}")
//            selectBook(position)

        }




        //AlertDialogBuilder
        val alertDialog: AlertDialog? = activity?.let {


            val builder = AlertDialog.Builder(activity)
            builder.setView(bookDirectoryDialogBinding.root)

            builder.apply {
                setPositiveButton("Select",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                        selectBook(selectedBookPosition)
                    })
                setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }
            // Set other dialog properties

            // Create the AlertDialog
            builder.create()
            builder.show()
        }
    }

    private fun selectBook(position: Int) {

        if(position > -1) {
            binding.book.setText(bookList[position])
        }
    }

    private fun filterOnKeyListener(filter: String): Boolean {
        bookList.clear()
        selectedBookPosition = -1

        for (book in books){
            if(book.bookName!!.contains(filter, true)){
                bookList.add(book.bookName.toString())
            }
        }

        bookListAdapter.notifyDataSetChanged();

        return false
    }



}
