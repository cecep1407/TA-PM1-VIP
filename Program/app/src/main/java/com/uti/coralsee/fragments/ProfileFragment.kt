package com.uti.coralsee.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uti.coralsee.LoginActivity
import com.uti.coralsee.R
import com.uti.coralsee.config.Lite
import com.uti.coralsee.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
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
        val sharedPreferences = requireContext().getSharedPreferences("namauser", Context.MODE_PRIVATE)
        // Ambil nama pengguna yang login dari penyimpanan lokal
        val loggedInUser = sharedPreferences.getString("logged_in_user", null)
        // Inflate the layout for this fragment
//        deklarasi variabel binding
        val binding = FragmentProfileBinding.inflate(inflater, container, false)


        val lite = Lite(requireContext())
        val Nama = lite.getNamaLengkap(loggedInUser)
        val Email = lite.getEmail(loggedInUser)


//       Menampilkan data
        binding.txtNamaProfile.text = Nama
        binding.txtEmailProfile.text = Email
        binding.txtUsernameProfile.text = loggedInUser

//       buat event edit profile
        binding.btnEditProfile.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frmContainer, EditProfileFragment()).commit()
        }

//        buat evet button logout
        binding.btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("logged_in_user")
            editor.apply()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}