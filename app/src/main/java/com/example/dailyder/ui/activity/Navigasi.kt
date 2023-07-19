package com.example.dailyder.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dailyder.ui.fragmen.AkunFragment
import com.example.dailyder.ui.fragmen.HomeFragment
import com.example.dailyder.R
import com.example.dailyder.databinding.ActivityNavigasiBinding

class Navigasi : AppCompatActivity() {
    private val binding: ActivityNavigasiBinding by lazy {
        ActivityNavigasiBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.akun -> {
                    replaceFragment(AkunFragment())
                }
                else ->{

                }
            }
            true
        }
        binding.add.setOnClickListener {
            startActivity(Intent(this@Navigasi, Add::class.java))
        }
    }

    private fun replaceFragment(home: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.activity_main_nav_host_fragment,home)
        fragmentTransaction.commit()

    }
}