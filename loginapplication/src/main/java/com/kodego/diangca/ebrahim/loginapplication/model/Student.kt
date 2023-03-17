package com.kodego.diangca.ebrahim.loginapplication.model

import com.kodego.diangca.ebrahim.loginapplication.R

class Student(var firstName:String = "Unknown",
              var lastName:String = "Unknown", val img:Int = R.drawable.profile_icon) {

    var yearStarted: Int = 0
}