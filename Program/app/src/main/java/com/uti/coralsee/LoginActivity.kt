package com.uti.coralsee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.coralsee.fragments.InputLoginFragment
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uti.coralsee.fragments.HomeFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Pemeriksaan login
        val sharedPreferences = getSharedPreferences("namauser", Context.MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("logged_in_user", null)
        if (loggedInUser != null) {
            // Pengguna sudah login, arahkan ke halaman utama
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)        } else {
            // Pengguna belum login, tampilkan halaman login
            supportFragmentManager.beginTransaction().add(R.id.frm_containter_login, InputLoginFragment()).commit()
        }
    }
}



