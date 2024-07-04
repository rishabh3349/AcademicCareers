package com.example.academiccareers


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FilteredView : AppCompatActivity(){
    private lateinit var rvMain: RecyclerView
    private lateinit var adapter: RvAdapterJob
    private lateinit var loadingLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filtered_view)
        supportActionBar?.hide()
        val location = intent.getStringExtra("location")
        val maxi = intent.getStringExtra("maxi")
        val mini = intent.getStringExtra("mini")
        val sub = intent.getStringExtra("sub")

        loadingLayout = findViewById(R.id.loading4)
        rvMain = findViewById(R.id.job_filtered)
        rvMain.layoutManager = LinearLayoutManager(this)

        loadingLayout.visibility = View.VISIBLE
        rvMain.visibility = View.GONE

    }
}