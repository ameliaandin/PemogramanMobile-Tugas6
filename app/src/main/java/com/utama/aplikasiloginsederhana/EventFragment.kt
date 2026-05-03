package com.utama.aplikasiloginsederhana
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.utama.aplikasiloginsederhana.databinding.FragmentEventBinding
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [EventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: EventRepository
    private lateinit var adapter: EventAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container,
            false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        repository = EventRepository(requireContext())
        binding.rvEvents.layoutManager =
            LinearLayoutManager(requireContext())
        loadEvents()
        binding.fabAddEvent.setOnClickListener {
            showAddEventDialog()
        }
    }
    // Muat ulang data event dari SQLite
    private fun loadEvents() {
        val events = repository.getAllEvents()
        adapter = EventAdapter(events) { event ->
            showEventDetailDialog(event)
        }
        binding.rvEvents.adapter = adapter
    }
    // Dialog untuk menampilkan detail event
    private fun showEventDetailDialog(event: Event) {
        AlertDialog.Builder(requireContext())
            .setTitle(event.name)
            .setMessage(
                "Tanggal : ${event.date}\n" +
                        "Tempat : ${event.location}\n" +
                        "Biaya : ${event.getFormattedPrice()}"
            )
            .setPositiveButton("Daftar Periksa") { _, _ ->
                repository.setRegistered(event.id, true)
                Toast.makeText(requireContext(),
                    "Berhasil mendaftar untuk: ${event.name}",
                    Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hapus Jadwal") { _, _ ->
                repository.deleteEvent(event.id)
                loadEvents() // Refresh daftar
                Toast.makeText(requireContext(),
                    "Jadwal dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Tutup", null)
            .show()
    }
    // Dialog untuk menambah event baru
    private fun showAddEventDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_event, null)
        AlertDialog.Builder(requireContext())
            .setTitle("Tambah Jadwal Baru")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val name =
                    dialogView.findViewById<EditText>(R.id.etEventName).text.toString().
                    trim()
                val date =
                    dialogView.findViewById<EditText>(R.id.etEventDate).text.toString().
                    trim()
                val location =
                    dialogView.findViewById<EditText>(R.id.etEventLocation).text.toString().
                    trim()
                val priceStr =
                    dialogView.findViewById<EditText>(R.id.etEventPrice).text.toString().
                    trim()
                if (name.isEmpty() || date.isEmpty() ||
                    location.isEmpty()) {
                    Toast.makeText(requireContext(),
                        "Nama Dokter, tanggal, dan lokasi wajib diisi!",
                        Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val newEvent = Event(
                    id = 0, // ID diisi otomatis oleh SQLite
                    name = name,
                    date = date,
                    location = location,
                    price = priceStr.toIntOrNull() ?: 0
                )
                repository.addEvent(newEvent)
                loadEvents() // Refresh daftar
                Toast.makeText(requireContext(),
                    "Jadwal berhasil ditambahkan!",
                    Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}