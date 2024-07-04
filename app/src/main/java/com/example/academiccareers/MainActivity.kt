package com.example.academiccareers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.academiccareers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subjectChoice: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        subjectChoice = intent.getStringExtra("subjectChoice") ?: ""
        val viewModel = ViewModelProvider(this).get(SubjectChoiceViewModel::class.java)
        viewModel.subjectChoice = subjectChoice
        replaceFragment(Home())
        binding.bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.m1 -> replaceFragment(Home())
                R.id.m2 -> replaceFragment(Alert())
                R.id.m3 -> replaceFragment(Job())
                R.id.m4 -> replaceFragment(Saved())
                else -> {}
            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}