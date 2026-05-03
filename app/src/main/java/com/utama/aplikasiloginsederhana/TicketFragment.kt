package com.utama.aplikasiloginsederhana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.utama.aplikasiloginsederhana.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {
    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionManager = SessionManager(requireContext())
        val username = sessionManager.getUsername() ?: "Pasien"
        binding.tvUsername.text = "Halo, $username"

        binding.rvMedicalRecords.layoutManager = LinearLayoutManager(requireContext())

        val medicalRecordList = listOf(
            MedicalRecord(1, username, "Cek Mata Mines", "10 Mei 2026", "Mata Kiri -1.5, Kanan -1.25"),
            MedicalRecord(2, username, "Cek Gula Darah", "11 Mei 2026", "Normal (95 mg/dL)"),
            MedicalRecord(3, username, "Rontgen Dada", "12 Mei 2026", "Paru-paru Bersih"),
            MedicalRecord(4, username, "Tes Kolesterol", "13 Mei 2026", "Tinggi (240 mg/dL)"),
            MedicalRecord(5, username, "Cek Tekanan Darah", "14 Mei 2026", "Hipertensi (150/90)"),
            MedicalRecord(6, username, "Tes Asam Urat", "15 Mei 2026", "Normal (5.0 mg/dL)")
        )

        val adapter = MedicalRecordAdapter(medicalRecordList) { record ->
            Toast.makeText(requireContext(), "Detail Hasil: ${record.result}", Toast.LENGTH_SHORT).show()
        }
        binding.rvMedicalRecords.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
