package com.uti.coralsee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.coralsee.databinding.ActivityMainBinding
import com.uti.coralsee.fragments.HomeFragment
import com.uti.coralsee.fragments.InputLoginFragment
import com.uti.coralsee.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout menggunakan binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().add(R.id.frmContainer, HomeFragment()).commit()
        binding.imghome.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frmContainer, HomeFragment()).commit()
        }

        binding.imgprofile.setOnClickListener {
            supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frmContainer, ProfileFragment()).commit()
        }
    }}