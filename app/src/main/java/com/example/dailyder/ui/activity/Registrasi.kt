package com.example.dailyder.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.dailyder.R
import com.example.dailyder.databinding.ActivityRegistrasiBinding
import com.example.dailyder.model.ResponseUser
import com.example.dailyder.network.ApiRetrofitServices
import kotlinx.android.synthetic.main.activity_registrasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registrasi : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Klik back register
        binding.backlogin.setOnClickListener {
            startActivity(Intent(this@Registrasi, Login::class.java))
            finish()
        }
        //visibility password
        binding.mata.setOnClickListener {
            if (edt_password.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())) {
                edt_password.transformationMethod = PasswordTransformationMethod.getInstance()
                mata.setImageResource(R.drawable.fi_eye_off)
            } else {
                edt_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                mata.setImageResource(R.drawable.fi_eye)
            }
        }

        //klik button register
        binding.btnRegistrasi.setOnClickListener {
            registrasi()
        }
    }

    private fun registrasi() {
        ApiRetrofitServices.endPoint.postRegistrasi(
            binding.edtUser.text.toString(),
            binding.edtUniversitas.text.toString(),
            binding.edtPassword.text.toString()
        ).enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful){
                    Toast.makeText(this@Registrasi,
                        response.body()!!.message, Toast.LENGTH_SHORT).show()
                    this@Registrasi.finish()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@Registrasi,
                    t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}