package com.example.booksalonappointment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksalonappointment.databinding.ServiceItemBinding
import com.example.booksalonappointment.model.remote.response.ServiceResponse

class ServiceAdapter (private var context: Context, private var services: List<ServiceResponse>):
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    lateinit var binding:ServiceItemBinding

    override fun getItemCount() = services.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ServiceItemBinding.inflate(layoutInflater, parent, false)
        return ServiceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
    holder.apply{
        val ser= services[position]
        tvCategory.text = "${ser.services.Haircuts[position].serviceName}"
        Glide.with(context).load(ser.services.Haircuts[position].servicePic)
            .into(binding.ivCategoryImage)

    }
    }

    inner class ServiceViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory: TextView= binding.tvCategory

    }


}