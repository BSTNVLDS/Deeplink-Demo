package com.accenture.deeplink

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.accenture.deeplink.databinding.ActivityMainBinding
import com.accenture.deeplink.service.ProductService

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnActionDeepLink.setOnClickListener {
            startActivity(DeepLinkActivity())
        }
        binding.btnActionDynamicLink.setOnClickListener {
            startActivity(DynamicLinkActivity())
        }
        ProductService.initList()
    }

    private fun startActivity(activity: Activity) {
        startActivity(Intent(this, activity.javaClass))
    }
}