package com.kodego.diangca.ebrahim.loginapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.kodego.diangca.ebrahim.loginapplication.R

data class Borrower(
    var firstName: String? = "",
    var lastName: String? = "", val img: Int = R.drawable.profile_icon
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    fun showDetails(): String {
        return "$lastName, $firstName"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeInt(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Borrower> {
        override fun createFromParcel(parcel: Parcel): Borrower {
            return com.kodego.diangca.ebrahim.loginapplication.model.Borrower(parcel)
        }

        override fun newArray(size: Int): Array<Borrower?> {
            return arrayOfNulls(size)
        }
    }

}

