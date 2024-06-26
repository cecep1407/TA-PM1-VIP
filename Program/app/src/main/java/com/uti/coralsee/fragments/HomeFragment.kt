package com.uti.coralsee.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.uti.coralsee.MainActivity
import com.uti.coralsee.R
import com.uti.coralsee.config.Lite
import com.uti.coralsee.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

        // Membuat instance Lite dengan menggunakan context fragment
        val lite = Lite(requireContext())

        // Memanggil metode getFirstName() dari instance Lite
        val firstName = lite.getFirstName(loggedInUser)


        // buat variable binding
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
//          Menampilkan nama pengguna yang login
        binding.txtName.text = firstName

//          Buat event untuk Jenis Terumbu Karang
        binding.cardCoral1.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).addToBackStack(null).replace(R.id.frmContainer, Coral1Fragment()).commit()
                (activity as MainActivity).hideNavigationBar()
        }
        binding.cardCoral2.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).addToBackStack(null).replace(R.id.frmContainer, Coral2Fragment()).commit()
                (activity as MainActivity).hideNavigationBar()
        }
        binding.cardCoral3.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).addToBackStack(null).replace(R.id.frmContainer, Coral3Fragment()).commit()
                (activity as MainActivity).hideNavigationBar()
        }
        binding.cardCoral4.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).addToBackStack(null).replace(R.id.frmContainer, Coral4Fragment()).commit()
                (activity as MainActivity).hideNavigationBar()
        }

//        Buat Event Untuk Artikel
        binding.artikel1.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).addToBackStack(null).replace(R.id.frmContainer, Artikel1Fragment()).commit()
                (activity as MainActivity).hideNavigationBar()
        }

        binding.artikel2.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).addToBackStack(null).replace(R.id.frmContainer, Artikel2Fragment()).commit()
                (activity as MainActivity).hideNavigationBar()
        }

        binding.artikel3.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right).addToBackStack(null).replace(R.id.frmContainer, Artikel3Fragment()).commit()
            (activity as MainActivity).hideNavigationBar()
        }
        // Set listener untuk tombol back
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Panggil finishAffinity() saat tombol back ditekan di HomeFragment
            requireActivity().finishAffinity()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showNavigationBar()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }
}