package com.utama.aplikasiloginsederhana

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.utama.aplikasiloginsederhana.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(requireContext())

        val username = sessionManager.getUsername()
        binding.tvProfileName.text = username
        binding.tvInfoName.text = username
        
        val repository = EventRepository(requireContext())
        val allEvents = repository.getAllEvents()
        val registeredCount = allEvents.count { it.isRegistered }

        binding.tvInfoPhone.text = "+62 812-3456-7890"
        binding.tvCountRegistered.text = allEvents.size.toString()
        binding.tvCountAttended.text = registeredCount.toString()
        binding.tvCountTickets.text = "6"

        binding.btnEditProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Fitur edit profil", Toast.LENGTH_SHORT).
            show()
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Keluar")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Keluar") { _, _ ->
                    sessionManager.clearSession()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
