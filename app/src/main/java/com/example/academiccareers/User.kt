package com.example.academiccareers

import android.net.Uri
import java.util.Date

class User {
    var name:String?=null
    var email:String?=null
    var uid:String?=null
    var currentSalary : String?=null
    var expectedSalary : String?=null
    var resume:String?=null
    var date: String? =null
    var maxi:Long=0



    constructor(){}
    constructor(name:String?,email:String?,uid:String?,currentSalary:String?,expectedSalary:String?,resume:String?,date: String?,maxi:Long){
        this.name=name
        this.email=email
        this.uid=uid
        this.currentSalary=currentSalary
        this.expectedSalary=expectedSalary
        this.resume=resume
        this.date=date
        this.maxi=maxi
    }
}
