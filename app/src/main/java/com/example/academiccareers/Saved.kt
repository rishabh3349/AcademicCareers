package com.example.academiccareers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth

class Saved : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved, container, false)
        auth = FirebaseAuth.getInstance()
        val logout:Button=view.findViewById(R.id.b5)
        val contact:Button=view.findViewById(R.id.b4)
        val edit:Button=view.findViewById(R.id.b1)
        edit.setOnClickListener {
            val intent=Intent(requireContext(),UserEdit::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener {
            auth.signOut()
            val intent=Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
        }
        contact.setOnClickListener {
            sendMessageToWhatsApp("+918619124387","Hey , I need Help")
        }
        return view
    }
    private fun sendMessageToWhatsApp(number: String?, message: String) {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$number&text=$message")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}