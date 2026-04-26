package com.utama.aplikasiloginsederhana
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.utama.aplikasiloginsederhana.databinding.FragmentProfileBinding
/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container,
            false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
// Set data profil
        val username = (activity as? HomeActivity)?.username
        val email = (activity as? HomeActivity)?.email
        binding.tvProfileName.text = username ?: "Pasien Klinik"
        binding.tvProfileEmail.text = email ?: "pasien@widyatama.ac.id"
        binding.tvInfoName.text = username ?: "Pasien Klinik"
        binding.tvInfoEmail.text = email ?: "pasien@widyatama.ac.id"
        binding.tvInfoPhone.text = "+62 800-0000-0000"
        // Statistik
        binding.tvCountRegistered.text = "0"
        binding.tvCountAttended.text = "0"
        binding.tvCountTickets.text = "0"
        binding.btnEditProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Fitur edit profil",
                Toast.LENGTH_SHORT).show()
        }
        binding.btnLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Berhasil keluar",
                Toast.LENGTH_SHORT).show()
            
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}