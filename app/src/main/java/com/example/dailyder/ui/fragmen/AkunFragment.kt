package com.example.dailyder.ui.fragmen

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.example.dailyder.R
import com.example.dailyder.databinding.FragmentAkunBinding
import com.example.dailyder.ui.activity.Login
import com.example.dailyder.ui.activity.Pengaturan_Akun
import com.example.dailyder.ui.activity.Ubah_Akun


class AkunFragment : Fragment() {
    private var _binding: FragmentAkunBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAkunBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ubahakun.setOnClickListener{
            startActivity(Intent(this@AkunFragment.requireContext(), Ubah_Akun::class.java))
        }
        binding.pengaturanAkun.setOnClickListener{
            startActivity(Intent(this@AkunFragment.requireContext(), Pengaturan_Akun::class.java))
        }
        binding.keluar.setOnClickListener {
            val massage = "Apakah Anda Ingin Keluar?"
            showCustom(massage)
        }
    }

    private fun showCustom(massage: String) {
        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_keluar)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val mes = dialog.findViewById<TextView>(R.id.tv_peringatan)
        val y = dialog.findViewById<TextView>(R.id.tv_ya)
        val t = dialog.findViewById<TextView>(R.id.tv_tidak)
        mes.text = massage
        y.setOnClickListener{
            startActivity(Intent(this@AkunFragment.requireContext(), Login::class.java))
            dialog.show()
        }
        t.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}