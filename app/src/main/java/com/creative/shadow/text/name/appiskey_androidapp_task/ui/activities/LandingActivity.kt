package com.creative.shadow.text.name.appiskey_androidapp_task.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.creative.shadow.text.name.appiskey_androidapp_task.R
import com.creative.shadow.text.name.appiskey_androidapp_task.databinding.ActivityLandingBinding
import com.creative.shadow.text.name.appiskey_androidapp_task.koin.DIComponents

class LandingActivity : AppCompatActivity() {
    private var bindingLanding: ActivityLandingBinding?=null
    private val binding get() = bindingLanding!!

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLanding= ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

//        keep data preLoaded
        loadDataHere()
    }

    private fun loadDataHere() {
        DIComponents.wallpaperViewViewModel.getWallpaperData("kitten")
    }

    private fun initViews() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.navController
    }
}