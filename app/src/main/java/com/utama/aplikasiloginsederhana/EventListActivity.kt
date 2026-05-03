package com.utama.aplikasiloginsederhana

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class EventListActivity : AppCompatActivity() {
    // Data statis (hardcoded)
    private val eventList = listOf(
        Event(1, "Jadwal Dr. Kaniz Pratama", "15 Mei 2026", "Klinik Widyatama", 50000),
        Event(2, "Jadwal Dr. Madinatul Munyah", "20 Mei 2026", "Klinik Widyatama", 75000),
        Event(3, "Jadwal Dr. Asep Ama Rosmana", "25 Mei 2026", "Klinik Widyatama", 60000),
        Event(4, "Jadwal Dr. Egilaurenzi", "1 Juni 2026", "Klinik Widyatama", 80000),
        Event(5, "Jadwal Dr. Selpiana", "10 Juni 2026", "Klinik Widyatama", 55000)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        val rvEvents = findViewById<RecyclerView>(R.id.rvEvents)
        rvEvents.layoutManager = LinearLayoutManager(this)

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val username = intent.getStringExtra("username")
        tvUsername.text = "Halo, ${username ?: "Guest"}"

        val adapter = EventAdapter(eventList) { event ->
            Toast.makeText(this, "Anda memilih: ${event.name}", Toast.LENGTH_SHORT).show()
        }
        rvEvents.adapter = adapter

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        // Set menu item "Event" sebagai aktif (selected)
        bottomNav.selectedItemId = R.id.nav_event

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Pindah ke HomeActivity
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                    finish() // Menutup EventListActivity agar tidak menumpuk
                    true
                }
                R.id.nav_event -> {
                    // Sudah di halaman Event
                    true
                }
                R.id.nav_ticket -> {
                    val intent = Intent(this, InventoryActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
