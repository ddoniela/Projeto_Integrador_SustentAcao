package com.generation.sustentacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.generation.sustentacao.databinding.ActivityMainBinding
import com.generation.sustentacao.databinding.FragmentFormBinding
import com.generation.sustentacao.databinding.FragmentListBinding
import com.generation.sustentacao.fragment.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeFragment = HomeFragment()
        val calendarFragment = CalendarFragment()
        val chatFragment = ChatFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_calendar -> makeCurrentFragment(calendarFragment)
                R.id.ic_chat -> makeCurrentFragment(chatFragment)
                R.id.ic_settings -> makeCurrentFragment(settingsFragment)
            }


            true
        }
    }

    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}