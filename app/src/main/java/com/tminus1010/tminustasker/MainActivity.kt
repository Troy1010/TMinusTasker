package com.tminus1010.tminustasker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tminus1010.tmcommonkotlin.coroutines.extensions.observe
import com.tminus1010.tminustasker.data.SelectedHostPageRepo
import com.tminus1010.tminustasker.databinding.ActivityMainBinding
import com.tminus1010.tminustasker.environment.android_wrapper.ActivityWrapper
import com.tminus1010.tminustasker.environment.android_wrapper.AndroidNavigationWrapperImpl
import com.tminus1010.tminustasker.ui.all_features.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var selectedHostPageRepo: SelectedHostPageRepo

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // # Setup top ActionBar
        setSupportActionBar(findViewById(R.id.toolbar_1))
        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            customView = layoutInflater.inflate(R.layout.action_bar_custom, null)
            setDisplayShowTitleEnabled(false)
        }
        // # Setup bottom NavBar
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        // # Mediation
        ActivityWrapper.activity = this
        AndroidNavigationWrapperImpl.nav = navController
        // # Event
        selectedHostPageRepo.flow.observe(lifecycleScope) { navigator.navTo(it ?: R.id.navigation_dashboard) }
        // # User Intent
        binding.navView.setOnItemSelectedListener { selectedHostPageRepo.set(it.itemId); false }
    }
}