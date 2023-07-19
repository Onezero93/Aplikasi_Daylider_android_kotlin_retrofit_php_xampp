package com.example.dailyder.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.dailyder.R
import com.example.dailyder.databinding.ActivityLoginBinding
import com.example.dailyder.model.ResponseUser
import com.example.dailyder.network.ApiRetrofitServices
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_akun.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var profil:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profil = getSharedPreferences("login", MODE_PRIVATE)
        if (profil.getString("namauser",null) != null) {
            startActivity(Intent(this@Login, pengaturan_akun::class.java))
        }
        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this@Login, Registrasi::class.java))
        }
        binding.mata.setOnClickListener {
            if (edt_password.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())) {
                edt_password.transformationMethod = PasswordTransformationMethod.getInstance()
                mata.setImageResource(R.drawable.fi_eye_off)
            } else {
                edt_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                mata.setImageResource(R.drawable.fi_eye)
            }
        }
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val user = binding.edtUser.text.toString()
        val password = binding.edtPassword.text.toString()
        if (user.isEmpty()) {
            binding.edtUser.error = "Isi terlebih dahulu Email anda"
            binding.edtUser.requestFocus()
            return
        } else if (password.isEmpty()) {
            binding.edtPassword.error = "Isi terlebih dahulu Password anda"
            binding.edtPassword.requestFocus()
            return
        }
        ApiRetrofitServices.endPoint.login(user,password).enqueue(object :Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                val res = response.body()!!
                    if (res.sukses) {
                        getSharedPreferences("login", MODE_PRIVATE)
                            .edit()
                            .putString("namauser",response.body()?.namauser)
                        Toast.makeText(
                            this@Login,
                            response.body()!!.message + " $user", Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@Login, Navigasi::class.java))
                    } else {
                        Toast.makeText(this@Login, response.body()!!.message, Toast.LENGTH_SHORT)
                            .show()
                    }
            }
            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
            }

        })
    }
}