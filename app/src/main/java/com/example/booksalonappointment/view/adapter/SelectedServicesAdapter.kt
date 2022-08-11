package com.example.booksalonappointment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.SelectedServiceItemBinding
import com.example.booksalonappointment.model.remote.Constants
import com.example.booksalonappointment.model.remote.response.service.Service
import com.example.booksalonappointment.model.remote.response.service.ServiceResponse

class SelectedServicesAdapter(
    private val context: Context,
    private val serviceItems: ServiceResponse
) :
    RecyclerView.Adapter<SelectedServicesAdapter.SelectedServicesViewHolder>() {

    lateinit var binding: SelectedServiceItemBinding

    override fun getItemCount() = serviceItems.services.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedServicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding =SelectedServiceItemBinding.inflate(layoutInflater, parent, false)
        return SelectedServicesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SelectedServicesViewHolder, position: Int) {
        holder.apply {
            val serviceItem = serviceItems.services[position]
            bind(serviceItem)

        }
    }


    inner class SelectedServicesViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(serviceItem: Service) {
            val title = v.findViewById<TextView>(R.id.sItem_title)
            val price = v.findViewById<TextView>(R.id.sItem_price)
            val duration = v.findViewById<TextView>(R.id.sItem_duration)
            title.text = serviceItem.serviceName
            price.text = serviceItem.cost.toString()
            duration.text = serviceItem.duration.toString()
            Glide.with(context)
                .load(Constants.BASE_IMAGE_URL + serviceItem.servicePic)
                .into(binding.ivCategoryImage)
        }
    }
}