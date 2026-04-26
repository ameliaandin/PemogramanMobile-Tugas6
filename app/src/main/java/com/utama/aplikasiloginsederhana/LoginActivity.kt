package com.utama.aplikasiloginsederhana

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // f) Validasi Input: Pastikan tidak kosong
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                
                // Langsung ke HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("email", "$username@widyatama.ac.id")
                startActivity(intent)
                finish() // Selesai LoginActivity
            }
        }

        // d) Navigasi ke halaman Registrasi (Intent Eksplisit)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
