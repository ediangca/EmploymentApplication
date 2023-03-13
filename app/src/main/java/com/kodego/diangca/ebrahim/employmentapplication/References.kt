package com.kodego.diangca.ebrahim.employmentapplication

import kotlin.properties.Delegates

class References {


    lateinit var name: String
    lateinit var title: String
    lateinit var company: String
    lateinit var phone: String

    constructor(name: String, title: String, company: String, phone: String){
        this.name = name
        this.title = title
        this.company = company
        this.phone = phone
    }
}