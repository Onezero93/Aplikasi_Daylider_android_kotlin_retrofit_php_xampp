package com.example.dailyder.ui.fragmen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailyder.ui.activity.Search
import com.example.dailyder.adapter.ListAdapter
import com.example.dailyder.databinding.FragmentHomeBinding
import com.example.dailyder.model.DataItem
import com.example.dailyder.model.ResponseCatatan
import com.example.dailyder.network.ApiRetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
//    private lateinit var profil:SharedPreferences
    private lateinit var dataListAdapter: ListAdapter
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rclData.layoutManager = LinearLayoutManager(requireContext())
        dataListAdapter = ListAdapter(arrayListOf())
        binding.rclData.adapter = dataListAdapter
        binding.sfContent.setOnRefreshListener {
            getList()
        }
        binding.btnSearce.setOnClickListener {
            startActivity(Intent(this@HomeFragment.requireContext(), Search::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
        getList()
    }
    private  fun getList(){
        binding.sfContent.isRefreshing = false
        ApiRetrofitServices.endPoint.getCatatan().enqueue(object : Callback<ResponseCatatan> {
            override fun onResponse(call: Call<ResponseCatatan>, response: Response<ResponseCatatan>) {
                if (response.isSuccessful){
                    val data: List<DataItem> = response.body()!!.data
                    onResultGet(data!!)
                    binding.sfContent.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<ResponseCatatan>, t: Throwable) {
            }

        })
    }

    private fun onResultGet(data: List<DataItem>) {
        val vertical = data
        dataListAdapter.setData(vertical)
        binding.sfContent.isRefreshing = true

    }
}