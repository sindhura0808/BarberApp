package com.example.booksalonappointment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.BarberServiceBookedItemBinding
import com.example.booksalonappointment.model.remote.Constants.BASE_IMAGE_URL
import com.example.booksalonappointment.model.remote.response.BarberService
import kotlin.math.roundToInt

class SummerServiceAdapter (private val context: Context,
                            private val services: ArrayList<BarberService>
) :
    RecyclerView.Adapter<SummerServiceAdapter.SummaryServiceViewHolder>() {

    lateinit var binding: BarberServiceBookedItemBinding

    override fun getItemCount() = services.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryServiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = BarberServiceBookedItemBinding.inflate(layoutInflater, parent, false)
        return SummaryServiceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SummaryServiceViewHolder, position: Int) {
        holder.apply {
            val barberService = services[position]
            bind(barberService)
        }
    }

    inner class SummaryServiceViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(barberService: BarberService) {

            val pref=context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
            var barberId=pref.getString("barber_id", " ")
            var barberName=pref.getString("barber_name", " ")
            var serviceId=pref.getString("service_id", " ")
            var serviceName=pref.getString("service_name", " ")
            var selectedDate=pref.getString("selected_Date", " ")
            var selectedTime=pref.getString("selectedTime", " ")

            val service_id= binding.tvServiceId
            val service_name= binding.tvServiceName
            val barber_id=binding.tvBarberId
            val barber_name= binding.tvBarberName
            val selected_date=binding.tvDate
            val selected_time= binding.tvTime

            service_id.text=serviceId
            service_name.text=serviceName
            barber_id.text=barberId
            barber_name.text=barberName
            selected_date.text=selectedDate
            selected_time.text=selectedTime
        }
    }
}
