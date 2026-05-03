package com.utama.aplikasiloginsederhana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.utama.aplikasiloginsederhana.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass for the Home screen of Klinik Widyatama.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionManager = SessionManager(requireContext())
        val username = sessionManager.getUsername()
        val repository = EventRepository(requireContext())
        
        // Menampilkan username pasien
        binding.tvUsername.text = username

        // Update ringkasan aktivitas
        val allEvents = repository.getAllEvents()
        val registeredCount = allEvents.count { it.isRegistered }
        
        binding.tvStatRegistered.text = allEvents.size.toString() // Total Jadwal Tersedia
        binding.tvStatAttended.text = registeredCount.toString() // Jumlah yg Didaftar
        binding.tvStatTickets.text = "6" // Contoh statis untuk Rekam Medis (sesuai list di TicketFragment)

        // Update konten jadwal terdekat jika ada yang didaftar
        val upcoming = allEvents.firstOrNull { it.isRegistered }
        if (upcoming != null) {
            binding.tvUpcomingEvent.text = upcoming.name
            binding.tvUpcomingDate.text = "${upcoming.date} - ${upcoming.location}"
        } else {
            binding.tvUpcomingEvent.text = "Belum ada pendaftaran"
            binding.tvUpcomingDate.text = "Silakan pilih jadwal di menu Jadwal"
        }

        // Navigasi menggunakan Navigation Component ke fragment Jadwal (nav_event)
        binding.cardEvent.setOnClickListener {
            findNavController().navigate(R.id.nav_event)
        }

        // Navigasi menggunakan Navigation Component ke fragment Rekam Medis (nav_ticket)
        binding.cardTicket.setOnClickListener {
            findNavController().navigate(R.id.nav_ticket)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
