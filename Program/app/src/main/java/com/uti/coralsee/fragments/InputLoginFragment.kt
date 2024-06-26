package com.uti.coralsee.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.uti.coralsee.MainActivity
import com.uti.coralsee.R
import com.uti.coralsee.config.Lite
import com.uti.coralsee.databinding.FragmentInputLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputLoginFragment : Fragment() {
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

        // Inflate the layout for this fragment
//        Buat Variable Binding
        val binding = FragmentInputLoginBinding.inflate(inflater, container, false)
//        Panggilan class Lite
        val lite = Lite(requireContext())


//        Buat Even Login
        binding.btnLogin.setOnClickListener {
            var username = binding.inpLoginUsername.text.toString()
            val pass = binding.inpLoginPassword.text.toString()
//            Kondisi Jika Input Kosong
            if (username.isEmpty() || pass.isEmpty()){
                Toast.makeText(requireContext(), "Username/Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                binding.inpLoginUsername.requestFocus()
                return@setOnClickListener
            }
            // Periksa apakah username terdaftar
            if (!lite.isUsernameExists(username)) {
                Toast.makeText(requireContext(), "Username belum terdaftar", Toast.LENGTH_SHORT).show()
                binding.inpLoginUsername.requestFocus()
                return@setOnClickListener
            } else {
            val loginSukses = lite.login(username, pass)
//            Kondisi Jika login berhasil
            if (loginSukses){
                val sharedPreferences = requireContext().getSharedPreferences("namauser", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                // Simpan nama pengguna yang login
                editor.putString("logged_in_user", username)
                editor.apply()
                Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
//            Kondisi Jika login gagal
            else{
                binding.inpLoginPassword.error = "Password Salah"
                binding.inpLoginPassword.requestFocus()
                return@setOnClickListener
            }
        }}

//          Buat Event SignUp
        binding.btnSignUp.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frm_containter_login, CreateUsernameFragment()).commit()
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
         * @return A new instance of fragment InputLoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputLoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}