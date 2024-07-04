package com.example.academiccareers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Date


class UserInfo : AppCompatActivity() {
    private lateinit var mDbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info)
        supportActionBar?.hide()
        firebaseAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        val name: EditText = findViewById(R.id.et1)
        val email: EditText = findViewById(R.id.et2)
        val currentSalary: EditText = findViewById(R.id.et3)
        val expectedSalary: EditText = findViewById(R.id.et4)
        val resume: EditText = findViewById(R.id.et5)
        val nextPage: Button = findViewById(R.id.next_page)

        nextPage.setOnClickListener {
            val name = name.text.toString().trim()
            if (name.length == 0) {
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            } else {
                val email = email.text.toString().trim()
                if (email.length == 0) {
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                } else {
                    val currentSalary = currentSalary.text.toString().trim()
                    if (currentSalary.length == 0) {
                        Toast.makeText(
                            this,
                            "Please enter your current salary ",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val expectedSalary = expectedSalary.text.toString().trim()
                        if (expectedSalary.length == 0) {
                            Toast.makeText(
                                this,
                                "Please enter your expected salary",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val resume=resume.text.toString().trim()
                            addUserToDatabase(
                                name,
                                email,
                                firebaseAuth.currentUser?.uid!!,
                                currentSalary,
                                expectedSalary,
                                resume
                            )
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    private fun addUserToDatabase(
        name: String,
        email: String,
        uid: String,
        currentSalary: String,
        expectedSalary: String,
        resume:String
    ) {
        var dateD: String? ="no"
        var maxi:Long?=0
        mDbRef = FirebaseDatabase.getInstance().getReference()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            mDbRef.child("user").child(currentUser.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            mDbRef.child("user").child(uid).addListenerForSingleValueEvent(object :
                                ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val user = snapshot.getValue(User::class.java)
                                    dateD=user?.date
                                    maxi=user?.maxi
                                }
                                override fun onCancelled(error: DatabaseError) {
                                    TODO("Not yet implemented")
                                }
                            })
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        // Handle database error
                    }
                })
        }
        //mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid)
            .setValue(User(name, email, uid, currentSalary, expectedSalary,resume,dateD,maxi!!))
    }
    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            // Check if user info exists in the database
            mDbRef.child("user").child(currentUser.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            // User info exists, navigate to MainActivity
                            startActivity(Intent(this@UserInfo, MainActivity::class.java))
                            finish() // Finish the UserInfo activity to prevent going back
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle database error
                    }
                })
        }
    }
}