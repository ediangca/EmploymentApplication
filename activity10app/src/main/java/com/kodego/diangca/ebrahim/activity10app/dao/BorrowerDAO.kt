package com.kodego.diangca.ebrahim.activity10app.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.widget.Toast
import com.kodego.diangca.ebrahim.activity10app.model.Borrower

interface BorrowerDAO {

    fun addBorrower(borrower: Borrower)
    fun getBorrowers() : ArrayList<Borrower>
}

class BorrowerDAOSQLImp(var context: Context): BorrowerDAO{

    override fun addBorrower(borrower: Borrower) {
        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.borrowerFirstName, borrower.firstName)
        contentValues.put(DatabaseHandler.borrowerLastName, borrower.lastName)
        contentValues.put(DatabaseHandler.borrowerContactNo, borrower.studentContactNo)

        val success = db.insert(DatabaseHandler.tableBorrower, null, contentValues)
        if(success > -1){
            Toast.makeText(context, "Borrower successfully added!", Toast.LENGTH_SHORT).show()
            db.close()
        }
    }

    override fun getBorrowers(): ArrayList<Borrower> {
        val borrowerList: ArrayList<Borrower> = ArrayList()

        val query = "SELECT  ${DatabaseHandler.borrowerId}, " +
                "${DatabaseHandler.borrowerFirstName}, " +
                "${DatabaseHandler.borrowerLastName}, " +
                "${DatabaseHandler.borrowerContactNo} " +
                "FROM ${DatabaseHandler.tableBorrower}"

        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var cursor: Cursor? = null

//        cursor = db.rawQuery(query, arrayOf("${DatabaseHandler.studentId}, ${DatabaseHandler.studentLastName}, ${DatabaseHandler.studentFirstName}"))
        try {
            cursor = db.rawQuery(query, null)
        }catch (sqlExemption : SQLiteException){
            db.close()
            return ArrayList()
        }
        var borrower: Borrower

        if(cursor.moveToFirst()){
            do{
                borrower = Borrower(cursor.getString(1), cursor.getString(2))
                borrower.borrowerId = cursor.getString(0)
                borrower.studentContactNo = cursor.getInt(3)

                borrowerList.add(borrower)

            }while (cursor.moveToNext())
        }

        db.close()

        return borrowerList
    }

}