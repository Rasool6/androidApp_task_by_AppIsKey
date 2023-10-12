package com.creative.shadow.text.name.appiskey_androidapp_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
 import com.creative.shadow.text.name.appiskey_androidapp_task.databinding.ActivityMainBinding
import com.creative.shadow.text.name.appiskey_androidapp_task.ui.activities.LandingActivity

class MainActivity : AppCompatActivity() {
    private var bindingMain:ActivityMainBinding?=null
    private val binding get() = bindingMain!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNameId.animate().alpha(1f).duration = 1500

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, LandingActivity::class.java))
            finish()
        },3000)

    }
}