package com.utama.aplikasiloginsederhana

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etRegisterUsername)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnSave = findViewById<Button>(R.id.btnSaveRegister)
        val btnBackToLogin = findViewById<Button>(R.id.btnBackToLogin)

        btnBackToLogin.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Username dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Password dan Konfirmasi Password tidak sama!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
                finish() // Kembali ke LoginActivity
            }
        }
    }
}
