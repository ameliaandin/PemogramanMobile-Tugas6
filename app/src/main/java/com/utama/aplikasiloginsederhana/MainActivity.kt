package com.utama.aplikasiloginsederhana

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        // Inisialisasi komponen
        val tvUsernameResult = findViewById<TextView>(R.id.tvUsernameResult)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        
        // Mengambil data dari intent
        val username = intent.getStringExtra("username")
        tvUsernameResult.text = username ?: "Guest"

        // Aksi tombol untuk masuk ke Beranda (Home)
        btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}
