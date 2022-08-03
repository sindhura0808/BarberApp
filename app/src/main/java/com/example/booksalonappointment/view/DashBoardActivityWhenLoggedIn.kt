package com.example.booksalonappointment.view


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.booksalonappointment.databinding.ActivityDashWhenLoggedInBinding


class DashBoardActivityWhenLoggedIn : AppCompatActivity() {
    private lateinit var binding:ActivityDashWhenLoggedInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashWhenLoggedInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(com.example.booksalonappointment.R.drawable.ic_baseline_menu_24)
        binding.navView.setNavigationItemSelectedListener { menuItems->
            menuItems.isChecked=true
            binding.drawerLayout.closeDrawers()
            when (menuItems.itemId){
                com.example.booksalonappointment.R.id.nav_bookappointment-> {
                    showToast("Book Appointment")
                }
                com.example.booksalonappointment.R.id.nav_ourservices-> {
                    showToast("Our Services")
                }
                com.example.booksalonappointment.R.id.nav_offers-> {
                  startActivity(Intent(this,OfferActivity::class.java))
                }
                com.example.booksalonappointment.R.id.nav_homecaretips-> {
                    showToast("Home Care Tips")
                }
                com.example.booksalonappointment.R.id.nav_howtoreach-> {
                    showToast("How To Reach?")
                }
                com.example.booksalonappointment.R.id.nav_workinghours -> {
                    showToast("working hours")
                }
                com.example.booksalonappointment.R.id.nav_logout -> {

                }
                com.example.booksalonappointment.R.id.nav_userrating-> {
                    showToast("rating")
                }
                com.example.booksalonappointment.R.id.nav_About-> {
                    showToast("about")
                }
            }
            true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.booksalonappointment.R.menu.menu_main, menu)
        return true
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