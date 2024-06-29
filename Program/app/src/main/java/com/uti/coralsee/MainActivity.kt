package com.uti.coralsee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.coralsee.databinding.ActivityMainBinding
import com.uti.coralsee.fragments.HomeFragment
import com.uti.coralsee.fragments.InputLoginFragment
import com.uti.coralsee.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout menggunakan binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().add(R.id.frmContainer, HomeFragment()).commit()
        binding.imghome.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frmContainer, HomeFragment()).commit()
        }

        binding.imgprofile.setOnClickListener {
            supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frmContainer, ProfileFragment()).commit()
        }
    }
    fun showNavigationBar() {
        binding.layButton.visibility = View.VISIBLE
        setBottomMargin(70)
    }

    fun hideNavigationBar() {
        binding.layButton.visibility = View.GONE
        setBottomMargin(0)
    }

    private fun setBottomMargin(marginDp: Int) {
        val marginPx = (marginDp * resources.displayMetrics.density).toInt()
        val params = binding.frmContainer.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = marginPx
        binding.frmContainer.layoutParams = params
    }
}