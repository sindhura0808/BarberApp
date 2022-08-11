package com.example.booksalonappointment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.TimeGridItemBinding
import com.example.booksalonappointment.databinding.ViewBookSelectDateBinding
import com.example.booksalonappointment.model.remote.Constants
import com.example.booksalonappointment.model.remote.response.service.ServiceCategory
import com.example.booksalonappointment.model.remote.response.time.CurrentAppointmentResponse
import com.example.booksalonappointment.model.remote.response.time.Slot
import com.example.booksalonappointment.model.remote.time.TimeSlot
import com.example.booksalonappointment.model.util.openDialog
import com.example.booksalonappointment.viewmodel.timeselection.TimeSelectionViewModel

class TimeSelectionAdapter(
    private val context: Context,
    private val slot: Map<String, Boolean>,
    var viewModel: TimeSelectionViewModel
) : RecyclerView.Adapter<TimeSelectionAdapter.TimeViewHolder>() {

    lateinit var binding: TimeGridItemBinding

    override fun getItemCount() = slot.size


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeSelectionAdapter.TimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = TimeGridItemBinding.inflate(layoutInflater, parent, false)
        return TimeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.apply {
            val slot = slot.keys.elementAt(position)
            holder.bind(slot, this@TimeSelectionAdapter.slot[slot]!!, position)

        }
    }

    fun freeSlots(slots: Int, position: Int): Int {
        for (i in 0 until slots) {
            if (position + i >= slot.size || slot[slot.keys.elementAt(position + i)] == true) {
                return i
            }
        }
        return -1
    }

    inner class TimeViewHolder(private val v: View) : RecyclerView.ViewHolder(binding.root) {
        fun bind(time: String, booked: Boolean, position: Int) {
            binding.tvTimeSlot.text = time.split("-")[0]
            if (booked) {
                binding.tvTimeSlot.setBackgroundResource(R.drawable.slotbooked)
            } else {
                binding.tvTimeSlot.setBackgroundResource(R.drawable.available)
            }
            binding.tvTimeSlot.setOnClickListener {
                val slots = 1
                val freeslots = freeSlots(slots, position)
                binding.tvTimeSlot.setBackgroundResource(R.drawable.booked)
                if (freeslots == -1) {
                    viewModel.setAppointmentsStartFrom(position)
                } else {
                    openDialog(
                        context as AppCompatActivity,
                        "Time You Have Selected is Not Available",
                        R.drawable.ic_baseline_not_interested_24,
                        "Please select an from available time"
                    )
                }
            }

            viewModel.appointmentsStartFromLiveData.observe(context as AppCompatActivity) {
                val slots = 1
                if (it != -1 && position in it until (it + slots)) {
                    binding.tvTimeSlot.setBackgroundResource(R.drawable.booked)
                } else {
                    if (booked) {
                        binding.tvTimeSlot.setBackgroundResource(R.drawable.slotbooked)
                    } else {
                        binding.tvTimeSlot.setBackgroundResource(R.drawable.available)
                    }
                }
            }

        }
    }
}