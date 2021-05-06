package ir.erfan_mh_at.android.reminder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import ir.erfan_mh_at.android.reminder.R
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        configure();
    }

    private fun configure(){
        bottomNavigationView.setupWithNavController(reminderNavHostFragment.findNavController())

        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.itemProfile ->{
                    Toast.makeText(this,"profile",Toast.LENGTH_SHORT).show()
                }
                R.id.itemSettings ->{
                    Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show()
                }
                R.id.itemAboutUs ->{
                    Toast.makeText(this,"about us",Toast.LENGTH_SHORT).show()
                }
                R.id.itemExit ->{
                    finish()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))return true
        return super.onOptionsItemSelected(item)
    }
}