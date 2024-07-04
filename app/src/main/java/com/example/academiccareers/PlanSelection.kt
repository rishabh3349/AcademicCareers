package com.example.academiccareers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class PlanSelection:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plan_select)
        supportActionBar?.hide()
        val c1:CardView=findViewById(R.id.c1)
        val c2:CardView=findViewById(R.id.c2)
        val c3:CardView=findViewById(R.id.c3)
        val c4:CardView=findViewById(R.id.c4)
        c1.setOnClickListener {
            val intent=Intent(this,Payment::class.java)
            intent.putExtra("amount","199.00")
            intent.putExtra("maxi","600000")
            startActivity(intent)
        }
        c2.setOnClickListener {
            val intent=Intent(this,Payment::class.java)
            intent.putExtra("amount","299.00")
            intent.putExtra("maxi","1000000")
            startActivity(intent)
        }
        c3.setOnClickListener {
            val intent=Intent(this,Payment::class.java)
            intent.putExtra("amount","399.00")
            intent.putExtra("maxi","1500000")
            startActivity(intent)
        }
        c4.setOnClickListener {
            val intent=Intent(this,Payment::class.java)
            intent.putExtra("amount","499.00")
            intent.putExtra("maxi","100000000")
            startActivity(intent)
        }
    }
}