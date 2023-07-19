package com.example.dailyder.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.dailyder.R
import com.example.dailyder.databinding.ActivityUbahAkunBinding
//import kotlinx.android.synthetic.main.activity_ubah_akun.*

class Ubah_Akun : AppCompatActivity() {
    private lateinit var binding: ActivityUbahAkunBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mata1.setOnClickListener {
//            if(edt_password_lama.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())){
//                edt_password_lama.transformationMethod = PasswordTransformationMethod.getInstance()
//                mata1.setImageResource(R.drawable.fi_eye_off)
//            }
//            else{
//                edt_password_lama.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                mata1.setImageResource(R.drawable.fi_eye)
//            }
//        }
//        binding.mata2.setOnClickListener {
//            if(edt_password_baru.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())){
//                edt_password_baru.transformationMethod = PasswordTransformationMethod.getInstance()
//                mata2.setImageResource(R.drawable.fi_eye_off)
//            }
//            else{
//                edt_password_baru.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                mata2.setImageResource(R.drawable.fi_eye)
//            }
//        }
//        binding.mata3.setOnClickListener {
//            if(edt_confirmasi_password.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())){
//                edt_confirmasi_password.transformationMethod = PasswordTransformationMethod.getInstance()
//                mata3.setImageResource(R.drawable.fi_eye_off)
//            }
//            else{
//                edt_confirmasi_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                mata3.setImageResource(R.drawable.fi_eye)
//            }
//        }
//    }
        }
    }
}