package com.example.academiccareers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RvAdapterAlert(private val context: Context, private val jobList:List<JobInfo>) : RecyclerView.Adapter<RvAdapterAlert.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.alert_card,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=jobList[position]
        holder.a1.text=currentItem.JobRole+" needed in "+currentItem.CoachingName
        holder.a2.text="Location : "+currentItem.Location
        holder.itemView.setOnClickListener {
            val intent = Intent(context, JobDescription::class.java)
            intent.putExtra("status", currentItem.Status)
            intent.putExtra("role", currentItem.JobRole)
            intent.putExtra("company", currentItem.CoachingName)
            intent.putExtra("location", currentItem.Location)
            intent.putExtra("job_type", currentItem.JobType)
            intent.putExtra("salary", currentItem.Salary)
            intent.putExtra("exam", currentItem.ExamName)
            intent.putExtra("joining", currentItem.Joining)
            intent.putExtra("experience", currentItem.Experience)
            intent.putExtra("number", currentItem.Number)
            intent.putExtra("maximum", currentItem.Maximum)
            context.startActivity(intent)
        }
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val a1: TextView =itemView.findViewById(R.id.a1)
        val a2: TextView =itemView.findViewById(R.id.a2)
    }
}