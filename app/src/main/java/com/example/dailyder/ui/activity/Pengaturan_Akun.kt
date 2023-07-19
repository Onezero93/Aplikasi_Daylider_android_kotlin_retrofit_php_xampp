package com.example.dailyder.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.dailyder.R
import com.example.dailyder.databinding.ActivityPengaturanAkunBinding
import com.example.dailyder.model.*
import com.example.dailyder.network.ApiRetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Pengaturan_Akun : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val binding by lazy { ActivityPengaturanAkunBinding.inflate(layoutInflater) }
    private var listSemester = ArrayList<String>()
    lateinit var jenisKelamin: String
    private lateinit var profil: SharedPreferences
    var id: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        profil = getSharedPreferences("login", MODE_PRIVATE)
        val nameuser = profil.getString("namauser",null)
        binding.edtUser.setText(nameuser)
        //spiner untuk semester
        getSpinerSemester()

        //tombol update
        binding.btnUpdateUser.setOnClickListener {
            clickUpdateUser()
        }

        //pilihan jenis kelamin
        binding.rgJeniskelamin.setOnCheckedChangeListener { _, checkedId ->

            if (checkedId == R.id.rb_pria)
                jenisKelamin = "Pria"
            else if (checkedId == R.id.rb_wanita)
                jenisKelamin = "Wanita"
            else jenisKelamin = ""
        }
        //galeri
        binding.ivCameraGaleri.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 100)
        }
    }

    //funncion clik update
    private fun clickUpdateUser() {
        ApiRetrofitServices.endPoint.postUser(
            binding.edtUser.text.toString(),
            binding.edtNim.text.toString(),
            binding.edtUniversitas.text.toString(),
            binding.spSmester.selectedItem.toString(),
            jenisKelamin,
            binding.edtNomorHp.text.toString()
        ).enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@Pengaturan_Akun,
                        response.body()!!.message, Toast.LENGTH_SHORT
                    ).show()
                    this@Pengaturan_Akun.finish()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(
                    this@Pengaturan_Akun,
                    t.message.toString(), Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    //funcion untuk memanggil api semester
    private fun getSpinerSemester() {
        ApiRetrofitServices.endPoint.getSemester().enqueue(object : Callback<ResponseSemester> {
            override fun onResponse(
                call: Call<ResponseSemester>,
                response: Response<ResponseSemester>
            ) {
                val listsemester: List<DataItemSemester> = response.body()!!.data
                listsemester?.forEach {
                    listSemester.add(it.semester)
                }
                binding.spSmester.onItemSelectedListener = this@Pengaturan_Akun
                val adaptersemester = ArrayAdapter(
                    this@Pengaturan_Akun,
                    android.R.layout.simple_spinner_dropdown_item,
                    listSemester
                )
                binding.spSmester.adapter = adaptersemester
            }

            override fun onFailure(call: Call<ResponseSemester>, t: Throwable) {
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    //untuk menampilkan gambar dari galeri
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            binding.ivCameraGaleri.setImageURI(data?.data)
        }
    }
}