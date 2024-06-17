package com.uti.coralsee.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uti.coralsee.R
import com.uti.coralsee.config.Lite
import com.uti.coralsee.databinding.FragmentEditProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        // Ambil nama pengguna yang login dari penyimpanan lokal
        val sharedPreferences =
            requireContext().getSharedPreferences("namauser", Context.MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("logged_in_user", null)

        // Mengambil data pengguna dari database

        val lite = Lite(requireContext())
        val Nama = lite.getNamaLengkap(loggedInUser)
        val Email = lite.getEmail(loggedInUser)
        val user = loggedInUser
        user?.let {
            binding.editTextFullName.setText(Nama)
            binding.editTextEmail.setText(Email)
        }


        // Buat Event tombol Save
        binding.btnSaveChanges.setOnClickListener {
            val updatedFullName = binding.editTextFullName.text.toString()
            val updatedEmail = binding.editTextEmail.text.toString()

            // Mengubah data pengguna di database
            if (loggedInUser != null) {
                val lite = Lite(requireContext())
                lite.updateUser(loggedInUser, updatedFullName, updatedEmail)

            }
            // Kembali ke halaman profil setelah menyimpan perubahan
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frmContainer, ProfileFragment()).commit()
            Toast.makeText(requireContext(), "Data Berhasil Diubah", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
        EditProfileFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
}
}