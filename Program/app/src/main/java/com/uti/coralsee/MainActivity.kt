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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inflate layout menggunakan binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().add(R.id.frmContainer, HomeFragment()).commit()
        binding.imghome.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frmContainer, HomeFragment()).commit()
        }

        binding.imgprofile.setOnClickListener {
            val sharedPreferences = getSharedPreferences("namauser", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("logged_in_user")
            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }}