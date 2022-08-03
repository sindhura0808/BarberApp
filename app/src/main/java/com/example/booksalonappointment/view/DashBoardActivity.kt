package com.example.booksalonappointment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

         supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener { menuItems->
            menuItems.isChecked=true
            binding.drawerLayout.closeDrawers()
            when (menuItems.itemId){
                R.id.nav_bookappointment-> {
                    showToast("Book Appointment")
                }
                R.id.nav_ourservices-> {
                    showToast("Our Services")
                }
                R.id.nav_offers-> {
                    startActivity(Intent(this,OfferActivity::class.java))
                }
                R.id.nav_homecaretips-> {
                    startActivity(Intent(this,HomeCareTipsActivity::class.java))
                }
                R.id.nav_howtoreach-> {
                    showToast("How To Reach?")
                }
                R.id.nav_workinghours -> {
                    startActivity(Intent(this, WorkingHoursActivity::class.java))
                }
                R.id.nav_notifications -> {
                showToast("Log Out")
            }
                R.id.nav_logout -> {
                    startActivity(Intent(this,LogOutActivity::class.java))
                }
                R.id.nav_userrating-> {
                    showToast("rating")
                }
                R.id.nav_About-> {
                    startActivity(Intent(this, AboutUSActivity::class.java))
                }
            }
            true
        }
    }

    private fun showToast(s:String)
    {
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home)
        {
            if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            else
            { binding.drawerLayout .openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)

    }
}