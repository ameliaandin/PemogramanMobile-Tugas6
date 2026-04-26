package com.utama.aplikasiloginsederhana

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class InventoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val rvInventory = findViewById<RecyclerView>(R.id.rvInventory)
        rvInventory.layoutManager = LinearLayoutManager(this)

        val username = intent.getStringExtra("username") ?: "Pasien"

        // Update greeting in UI
        findViewById<TextView>(R.id.tvUsername)?.text = "Halo, $username"

        val medicalRecordList = listOf(
            MedicalRecord(1, username, "Cek Mata Mines", "10 Mei 2026", "Mata Kiri -1.5, Kanan -1.25"),
            MedicalRecord(2, username, "Cek Gula Darah", "11 Mei 2026", "Normal (95 mg/dL)"),
            MedicalRecord(3, username, "Rontgen Dada", "12 Mei 2026", "Paru-paru Bersih"),
            MedicalRecord(4, username, "Tes Kolesterol", "13 Mei 2026", "Tinggi (240 mg/dL)"),
            MedicalRecord(5, username, "Cek Tekanan Darah", "14 Mei 2026", "Hipertensi (150/90)"),
            MedicalRecord(6, username, "Tes Asam Urat", "15 Mei 2026", "Normal (5.0 mg/dL)")
        )
        
        val adapter = MedicalRecordAdapter(medicalRecordList) { record ->
            Toast.makeText(this, "Detail Hasil: ${record.result}", Toast.LENGTH_SHORT).show()
        }
        rvInventory.adapter = adapter

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_ticket

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_event -> {
                    val intent = Intent(this, EventListActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_ticket -> true
                else -> false
            }
        }
    }
}
