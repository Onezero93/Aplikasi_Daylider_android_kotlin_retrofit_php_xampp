package com.example.dailyder.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailyder.adapter.ListAdapter
import com.example.dailyder.databinding.ActivitySearchBinding
import com.example.dailyder.model.DataItem
import com.example.dailyder.model.ResponseCatatan
import com.example.dailyder.network.ApiRetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Search : AppCompatActivity() {
    private lateinit var dataListAdapter: ListAdapter
    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rclData.layoutManager = LinearLayoutManager(this)
        dataListAdapter = ListAdapter(arrayListOf())
        binding.rclData.adapter = dataListAdapter
        binding.imgSearce.setOnClickListener {
            if (binding.edtSearce.text.isEmpty()){
                Toast.makeText(this@Search, "Berhasil", Toast.LENGTH_SHORT).show()
            }
            else{
//                getList(binding.edtSearce.text.toString())
//                Toast.makeText(this@Search, "gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        getList()
    }

    private fun getList() {
        ApiRetrofitServices.endPoint.getCatatan().enqueue(object : Callback<ResponseCatatan> {
            override fun onResponse(call: Call<ResponseCatatan>, response: Response<ResponseCatatan>) {
                if (response.isSuccessful){
                    val data: List<DataItem> = response.body()!!.data
                    onResultGet(data!!)
                }
            }

            override fun onFailure(call: Call<ResponseCatatan>, t: Throwable) {
            }

        })
    }

    private fun onResultGet(data: List<DataItem>) {
        val vertical = data
        dataListAdapter.setData(vertical)

    }
}