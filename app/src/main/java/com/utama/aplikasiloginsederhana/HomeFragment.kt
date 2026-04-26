package com.utama.aplikasiloginsederhana
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.utama.aplikasiloginsederhana.databinding.FragmentHomeBinding
/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container,
            false)
        return binding.root
    }
    // onViewCreated: logika UI setelah view siap
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
// Set data dinamis
        val username = (activity as? HomeActivity)?.username
        binding.tvUsername.text = username ?: "Pasien Klinik"
        binding.tvUpcomingEvent.text = "Pemeriksaan Umum"
        binding.tvUpcomingDate.text = "Belum ada jadwal"
// Navigasi dari card shortcut ke fragment Event
        binding.cardEvent.setOnClickListener {
            findNavController().navigate(R.id.nav_event)
        }
// Navigasi dari card shortcut ke fragment Tiket
        binding.cardTicket.setOnClickListener {
            findNavController().navigate(R.id.nav_ticket)
        }

        binding.cardUpcomingEvent.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka detail jadwal periksa", Toast.LENGTH_SHORT).show()
        }

        binding.tvStatRegistered.setOnClickListener {
            Toast.makeText(requireContext(), "Total kunjungan Anda", Toast.LENGTH_SHORT).show()
        }

        binding.tvStatAttended.setOnClickListener {
            Toast.makeText(requireContext(), "Daftar resep obat Anda", Toast.LENGTH_SHORT).show()
        }

        binding.tvStatTickets.setOnClickListener {
            Toast.makeText(requireContext(), "Nomor antrian saat ini", Toast.LENGTH_SHORT).show()
        }
    }
    // Wajib: bebaskan binding saat view dihancurkan
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}