package com.example.academiccareers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class SubjectChoose:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subject_choose)
        supportActionBar?.hide()
        val c1:Button=findViewById(R.id.c1)
        val c2:Button=findViewById(R.id.c2)
        val c3:Button=findViewById(R.id.c3)
        val c4:Button=findViewById(R.id.c4)

        c1.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("subject","c1")
            startActivity(intent)
        }
        c2.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("subject","c2")
            startActivity(intent)
        }
        c3.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("subject","c3")
            startActivity(intent)
        }
        c4.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("subject","c4")
            startActivity(intent)
        }
    }
}