package com.example.academiccareers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import dev.shreyaspatil.easyupipayment.model.TransactionDetails
import dev.shreyaspatil.easyupipayment.model.TransactionStatus

class Payment:AppCompatActivity(), PaymentStatusListener {

    private lateinit var easyUpiPayment: EasyUpiPayment
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var maxim:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_id)
        supportActionBar?.hide()
        val amount = intent.getStringExtra("amount")
        val maximum = intent.getStringExtra("maxi")
        maxim=maximum.toString()

        val am:TextView=findViewById(R.id.amount)
        am.text="Amount = Rs. $amount"
        val upi:EditText=findViewById(R.id.upi_id)
        val pay:Button=findViewById(R.id.pay)

        pay.setOnClickListener {
            val upi_id:String=upi.text.toString()
            if(upi_id.isEmpty()){
                Toast.makeText(this,"Enter Upi Id",Toast.LENGTH_SHORT).show()
            }
            else{
                makePayment(amount,upi_id)
            }
        }
    }

    private fun makePayment(amount: String?, upiId: String) {


        val payeeVpa = upiId
        val payeeName = "abcd"
        val transactionId = "abcd"
        val transactionRefId = "abcd"
        val payeeMerchantCode = "saklan-hr-academic-careers@ybl"
        val description = "courses"

        try {
            easyUpiPayment = EasyUpiPayment(this) {
                this.payeeVpa = "saklan-hr-academic-careers@ybl"
                this.payeeName = payeeName
                this.transactionId = transactionId
                this.transactionRefId = transactionRefId
                this.payeeMerchantCode = payeeMerchantCode
                this.description = description
                this.amount = amount.toString()
            }
            easyUpiPayment.setPaymentStatusListener(this)
            easyUpiPayment.startPayment()
        } catch (e: Exception) {
            e.printStackTrace()
            toast("Error: ${e.message}")
        }


    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        Log.d("TransactionDetails", transactionDetails.toString())

        when (transactionDetails.transactionStatus) {
            TransactionStatus.SUCCESS -> onTransactionSuccess()
            TransactionStatus.FAILURE -> onTransactionFailed()
            TransactionStatus.SUBMITTED -> onTransactionSubmitted()
        }
    }

    override fun onTransactionCancelled() {
        // Payment Cancelled by User
        toast("Cancelled by user")
    }

    private fun onTransactionSuccess() {
        // Payment Success
        toast("Success")
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser!!.uid
        database.child("user").child(userId).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                user?.maxi=maxim.toLong()
                user?.date="yes"
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
    private fun onTransactionSubmitted() {
        // Payment Pending
        toast("Pending | Submitted")
    }
    private fun onTransactionFailed() {
        // Payment Failed
        toast("Failed")
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}