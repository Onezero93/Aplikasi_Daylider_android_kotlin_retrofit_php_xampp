package com.example.dailyder.ui.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.dailyder.databinding.ActivityAddBinding
import com.example.dailyder.model.*
import com.example.dailyder.network.ApiRetrofitServices
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Add : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private var listSemester = ArrayList<String>()
    private val calender = Calendar.getInstance()
    private val formateDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
    private val formateTime = SimpleDateFormat("HH:mm", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //spiner untuk semester
        getSpinerSemester()

        //calender tanggal tugas
        binding.kalender1.setOnClickListener {
            DatePickerDialog(
                this,
                this,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.kalender2.setOnClickListener {
            val calender2 = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            calender2.show(supportFragmentManager, "datepicker")
            calender2.addOnPositiveButtonClickListener {
                val formatdate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
                binding.edtTlpengumpulan.text = formatdate.format(Date(it).time)
            }
        }
        //wakru pengumpulan
        binding.jam.setOnClickListener {
            TimePickerDialog(
                this,
                this,
                calender.get(Calendar.HOUR_OF_DAY),
                calender.get(Calendar.MINUTE),
                false
            ).show()
        }
        binding.btnCrate.setOnClickListener {
            ApiRetrofitServices.endPoint.postCatatan(
                binding.edtMatakuliah.text.toString(),
                binding.edtUniversitas.text.toString(),
                binding.spSmester.selectedItem.toString(),
                binding.edtTltugas.text.toString(),
                binding.edtTlpengumpulan.text.toString(),
                binding.edtWaktupengumpulan.text.toString(),
                binding.edtDeskripsi.text.toString()
            ).enqueue(object :Callback<ResponseCatatan>{
                override fun onResponse(
                    call: Call<ResponseCatatan>,
                    response: Response<ResponseCatatan>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@Add,
                            response.body()!!.message, Toast.LENGTH_SHORT).show()
                        this@Add.finish()
                    }
                }

                override fun onFailure(call: Call<ResponseCatatan>, t: Throwable) {
                    Toast.makeText(this@Add,
                        t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
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
                binding.spSmester.onItemSelectedListener = this@Add
                val adaptersemester = ArrayAdapter(
                    this@Add,
                    android.R.layout.simple_spinner_dropdown_item,
                    listSemester
                )
                binding.spSmester.adapter = adaptersemester
            }

            override fun onFailure(call: Call<ResponseSemester>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    //dialog calender
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.e("calender", "$year $month $dayOfMonth")
        calender.set(year, month, dayOfMonth)
        displayFormatedData(calender.timeInMillis)
    }

    //format tampilan kalender
    private fun displayFormatedData(timeInMillis: Long) {
        binding.edtTltugas.text = formateDate.format(timeInMillis)
        Log.i("format", timeInMillis.toString())
    }

    //dialog jam
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Log.e("calender", "$hourOfDay $minute")
        calender.apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
        displayFormatedDataTime(calender.timeInMillis)
    }

    //format tammpilan jam
    private fun displayFormatedDataTime(timeInMillistime: Long) {
        binding.edtWaktupengumpulan.text = formateTime.format(timeInMillistime)
        Log.i("format", timeInMillistime.toString())
    }
}