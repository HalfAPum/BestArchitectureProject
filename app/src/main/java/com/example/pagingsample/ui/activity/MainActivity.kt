package com.example.pagingsample.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(
            R.id.fragment_container_view
        ) as NavHostFragment
    }

    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.bottomNav.setup(navController)
    }

    private fun NavigationBarView.setup(navController: NavController) {
        setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, _, _ ->
            controller.backQueue.forEach { navEntry ->
                binding.bottomNav.menu.forEach { menuItem ->
                    if (navEntry.destination.id == menuItem.itemId) {
                        menuItem.isChecked = true
                    }
                }
            }
        }
    }
}