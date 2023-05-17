package com.kodego.diangca.ebrahim.loginapplication.model

import android.os.Parcelable
import com.kodego.diangca.ebrahim.loginapplication.R

data class Librarian(
    var firstName: String? = null,
    var lastName: String? = null, val img: Int = R.drawable.profile_icon
) : User("",""), Parcelable {

}

