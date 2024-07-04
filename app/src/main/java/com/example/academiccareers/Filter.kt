package com.example.academiccareers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Filter:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filter)
        supportActionBar?.hide()

        val location: EditText = findViewById(R.id.ett1)
        val maxi: EditText = findViewById(R.id.ett2)
        val mini: EditText = findViewById(R.id.ett3)
        val sub: EditText = findViewById(R.id.ett4)
        val search: Button = findViewById(R.id.searchh)
        search.setOnClickListener {
            val location=location.text.toString().trim()
            val maxi=maxi.text.toString().trim()
            val mini=mini.text.toString().trim()
            val sub=sub.text.toString().trim()
            val intent=Intent(this,FilteredView::class.java)
            intent.putExtra("location",location)
            intent.putExtra("maxi",maxi)
            intent.putExtra("mini",mini)
            intent.putExtra("sub",sub)
            startActivity(intent)
        }
    }
}