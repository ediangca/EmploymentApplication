package com.kodego.diangca.ebrahim.loginapplication.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.loginapplication.MainActivity
import com.kodego.diangca.ebrahim.loginapplication.databinding.BorrowerItemBinding
import com.kodego.diangca.ebrahim.loginapplication.model.Borrower

class BorrowerAdapter(var mainActivity: MainActivity, var borrowers: ArrayList<Borrower>) :
    RecyclerView.Adapter<BorrowerAdapter.BorrowerViewHolder>() {


    override fun getItemCount(): Int {
        return borrowers.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BorrowerAdapter.BorrowerViewHolder {
        val itemBinding =
            BorrowerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BorrowerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BorrowerAdapter.BorrowerViewHolder, position: Int) {
        holder.bindStudent(borrowers[position])
    }


    inner class BorrowerViewHolder(
        private val itemBinding: BorrowerItemBinding,
    ) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var borrower = Borrower()
        fun bindStudent(borrower: Borrower) {
            this.borrower = borrower
            itemBinding.studentName.text = "${borrower.lastName}, ${borrower.firstName}"
//            itemBinding.profilePic.setImageResource(student.img)

            val imageView = itemBinding.profilePic
            var bitmap = (AppCompatResources.getDrawable(
                mainActivity,
                borrower.img
            ) as BitmapDrawable).bitmap
            val imageRounded = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
            val canvas = Canvas(imageRounded)
            val paint = Paint()
            paint.isAntiAlias = true
            paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            canvas.drawRoundRect(
                RectF(10F, 10F, bitmap.width.toFloat(), bitmap.height.toFloat()),
                200F, 200F, paint
            ) // Round Image Corner 100 100 100 100
            imageView.setImageBitmap(imageRounded)

            itemBinding.btnRemove.setOnClickListener {
                btnRemoveOnClickListener(itemBinding, adapterPosition, borrower)
            }
        }

        override fun onClick(view: View?) {
            if (view!=null)
                Snackbar.make(
                    itemBinding.root,
                    "${itemBinding.profilePic} ${borrower.lastName}, ${borrower.firstName}",
                    Snackbar.LENGTH_SHORT
                ).show()
        }

    }

    private fun btnRemoveOnClickListener(
        itemBinding: BorrowerItemBinding,
        positionAdapter: Int,
        borrower: Borrower,
    ) {

        var alertDialogBuilder = AlertDialog.Builder(mainActivity)
        alertDialogBuilder.setTitle("Delete?")
        alertDialogBuilder.setMessage("Are you sure you want to delete ${borrowers[positionAdapter].lastName}, ${borrowers[positionAdapter].firstName}")
        alertDialogBuilder.setNegativeButton("Cancel", null)
        alertDialogBuilder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, position: Int) {
                borrowers.removeAt(positionAdapter)
                mainActivity.removeBorrowerToMain(borrower)
                notifyItemRemoved(positionAdapter);
                notifyItemRangeChanged(positionAdapter, itemCount);
                Snackbar.make(
                    itemBinding.root,
                    "Data has been successfully removed.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }).show()
    }


}