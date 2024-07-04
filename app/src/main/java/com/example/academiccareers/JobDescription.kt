package com.example.academiccareers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class JobDescription : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_description)
        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        val role = intent.getStringExtra("role")
        val company = intent.getStringExtra("company")
        val location = intent.getStringExtra("location")
        val salary = intent.getStringExtra("salary")
        val exam = intent.getStringExtra("exam")
        val joining = intent.getStringExtra("joining")
        val experience = intent.getStringExtra("experience")
        val number = intent.getStringExtra("number")
        val maximum = intent.getStringExtra("maximum")

        val z3: TextView = findViewById(R.id.z3)
        z3.text = company
        val z5: TextView = findViewById(R.id.z5)
        z5.text = role
        val z7: TextView = findViewById(R.id.z7)
        z7.text = salary
        val z9: TextView = findViewById(R.id.z9)
        z9.text = location
        val z12: TextView = findViewById(R.id.z12)
        z12.text = "Experience : " + experience
        val z13: TextView = findViewById(R.id.z13)
        z13.text = "Level : " + exam
        val z14: TextView = findViewById(R.id.z14)
        z14.text = "Joining : " + joining


        val apply: Button = findViewById(R.id.apply)

        val userId = auth.currentUser!!.uid
        database.child("user").child(userId).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                val userName = user?.name
                val email = user?.email
                val curr = user?.currentSalary
                val exp = user?.expectedSalary
                val resume = user?.resume
                val pNumber = "+91$number"
                apply.setOnClickListener {
                    if (user?.date == "yes") {
                        if (user.maxi <= maximum!!.toLong()) {
                            val message =
                                "Name: $userName , Email: $email , Current Salary: $curr , Expected Salary: $exp , Resume: $resume"
                            sendMessageToWhatsApp(pNumber, message)
                        } else {
                            Toast.makeText(
                                this@JobDescription,
                                "Please enter name",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@JobDescription, PlanSelection::class.java)
                            startActivity(intent)
                        }
                    } else {
                        val intent = Intent(this@JobDescription, PlanSelection::class.java)
                        startActivity(intent)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun sendMessageToWhatsApp(number: String?, message: String) {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$number&text=$message")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}