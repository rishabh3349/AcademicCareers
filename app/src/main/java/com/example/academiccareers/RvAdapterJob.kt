package com.example.academiccareers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapterJob(private val context: Context, private val jobList: List<JobInfo>) :
    RecyclerView.Adapter<RvAdapterJob.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jobList[position]
        holder.status.text = currentItem.Status
        holder.role.text = currentItem.JobRole
        holder.company.text = currentItem.CoachingName
        holder.location.text = currentItem.Location
        holder.job_type.text = currentItem.JobType
        holder.salary.text = currentItem.Salary
        holder.job_des.setOnClickListener {
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
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val status: TextView = itemView.findViewById(R.id.status)
        val role: TextView = itemView.findViewById(R.id.role)
        val company: TextView = itemView.findViewById(R.id.company)
        val location: TextView = itemView.findViewById(R.id.location)
        val job_type: TextView = itemView.findViewById(R.id.job_type)
        val salary: TextView = itemView.findViewById(R.id.salary)
        val job_des: TextView = itemView.findViewById(R.id.new_page)
    }
}