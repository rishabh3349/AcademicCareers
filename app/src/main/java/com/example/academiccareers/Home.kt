package com.example.academiccareers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home : Fragment() {

    private lateinit var rvMain: RecyclerView
    private lateinit var adapter: RvAdapterJob
    private lateinit var loadingLayout: View


    var BASE_URL = "https://script.googleusercontent.com/macros/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        rvMain = view.findViewById(R.id.home_rv)
        rvMain.layoutManager = LinearLayoutManager(requireContext())
        loadingLayout = view.findViewById(R.id.loading1)
        getAllData()
        return view
    }

    private fun getAllData() {
        loadingLayout.visibility = View.VISIBLE
        rvMain.visibility = View.GONE
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retroData = retrofit.getData()

        retroData.enqueue(object : Callback<GetData> {
            override fun onResponse(call: Call<GetData>, response: Response<GetData>) {
                // Check if the fragment is still attached to its activity
                if (!isAdded) return

                val data = response.body()?.data ?: return

                adapter = RvAdapterJob(requireContext(), data)
                rvMain.adapter = adapter
                loadingLayout.visibility = View.GONE
                rvMain.visibility = View.VISIBLE

                Log.d("data", data.toString())
            }

            override fun onFailure(call: Call<GetData>, t: Throwable) {
                // Check if the fragment is still attached to its activity
                if (!isAdded) return

                Log.e("API Error", "Failed to fetch data", t)
                // Handle the failure, e.g., show an error message
            }
        })
    }

}
