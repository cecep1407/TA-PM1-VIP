package com.uti.coralsee.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uti.coralsee.R
import com.uti.coralsee.config.Lite
import com.uti.coralsee.databinding.FragmentCreateUsernameBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateUsernameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateUsernameFragment : Fragment() {
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

//Deklarasi Binding
        val binding = FragmentCreateUsernameBinding.inflate(inflater, container, false)
//Panggil class Lite
        val lite = Lite(requireContext())
//        Event Button SignUp
        binding.btnCreateUser.setOnClickListener(){
        val nama = binding.inpName.text.toString()
        val email = binding.inpEmail.text.toString()
        val username = binding.inpUsername.text.toString()
        val password = binding.inpPassword.text.toString()
        val pengguna = Lite.Pengguna(nama,email, username, password)
        val hasil = lite.insertPengguna(pengguna)
        if (hasil != -1L) {
            Toast.makeText(requireContext(), "Sign Up Berhasil, Silakan Login", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Gagal memasukkan data", Toast.LENGTH_SHORT).show()
        }
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frm_containter_login, InputLoginFragment()).commit()
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
         * @return A new instance of fragment CreateUsernameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateUsernameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}