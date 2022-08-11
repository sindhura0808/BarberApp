package com.example.booksalonappointment.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksalonappointment.databinding.ServiceItemBinding
import com.example.booksalonappointment.model.remote.Constants.BASE_IMAGE_URL
import com.example.booksalonappointment.model.remote.response.service.ServiceCategory
import com.example.booksalonappointment.model.remote.response.service.ServiceCategoryResponse
import com.example.booksalonappointment.model.remote.response.service.ServiceResponse
import com.example.booksalonappointment.view.SelectedServiceActivity

class ServiceAdapter (private var context: Context, private var services: ServiceCategoryResponse):
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    lateinit var binding:ServiceItemBinding

    override fun getItemCount() = services.serviceCategories.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ServiceItemBinding.inflate(layoutInflater, parent, false)
        return ServiceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
    holder.apply{
        val ser= services.serviceCategories[position]
        bind(ser)

        itemView.setOnClickListener{
            val catID= ser.categoryId.toString()
        val intent=Intent(context, SelectedServiceActivity::class.java)
            intent.putExtra("catID" ,catID)
           context.startActivity(intent)
        }
    }
    }

    inner class ServiceViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory: TextView= binding.tvCategory
        val imageview: AppCompatImageView = binding.ivCategoryImage
         fun bind(service: ServiceCategory)
         {
             val tvCategory: TextView= binding.tvCategory
             tvCategory.text=service.category
             Glide.with(context)
                 .load(BASE_IMAGE_URL + service.categoryImage)
                 .into(binding.ivCategoryImage)
         }
    }
    }