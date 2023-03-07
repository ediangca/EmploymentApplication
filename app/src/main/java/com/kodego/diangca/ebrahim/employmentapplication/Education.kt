package com.kodego.diangca.ebrahim.employmentapplication

import kotlin.properties.Delegates

class Education {

    lateinit var school: String
    lateinit var address: String
    var yearAttended by Delegates.notNull<Int>()
    lateinit var degreeReceived: String
    lateinit var major: String

    constructor(school: String, address: String, yearAttended: Int, degreeReceived: String, major: String){
        this.school = school
        this.address = address
        this.yearAttended = yearAttended
        this.degreeReceived = degreeReceived
        this.major = major
    }
}