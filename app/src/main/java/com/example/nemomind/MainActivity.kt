package com.example.nemomind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.nemomind.databinding.ActivityMainBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val bottomNavigationView: ChipNavigationBar = binding.bottomNavigation

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView.setItemSelected(R.id.home_main, true)

        bottomNavigationView.setOnItemSelectedListener { itemId ->
            when (itemId) {
                R.id.home -> {
                    navController.navigate(R.id.home_main)
                }

                R.id.CheckList -> {
                    navController.navigate(R.id.checkKList)
                }

                R.id.profile -> {
                    navController.navigate(R.id.profile)
                }



            }


        }
    }
}

