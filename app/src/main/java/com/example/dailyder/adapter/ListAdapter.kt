package com.example.dailyder.adapter


import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyder.R
import com.example.dailyder.databinding.FragmentHomeBinding
import com.example.dailyder.databinding.ItemListcatatanBinding
import com.example.dailyder.model.DataItem
import com.example.dailyder.model.ResponseCatatan
import com.example.dailyder.network.ApiRetrofitServices
import com.example.dailyder.ui.activity.Update
import com.example.dailyder.ui.fragmen.HomeFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListAdapter(
    var dataList: ArrayList<DataItem>
) : RecyclerView.Adapter<ListAdapter.AplikasiViewHolder>() {
    class AplikasiViewHolder(val binding: ItemListcatatanBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AplikasiViewHolder(
        ItemListcatatanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: AplikasiViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.tvMatakuliah.text = data.matakuliah
        holder.binding.tvSemester.text = data.semester
        holder.binding.tvUniversitas.text = data.universitas
        holder.binding.tvTanggaltugas.text = data.tanggal_tugas
        holder.binding.tvTanggalpengumpulan.text = data.tanggal_pengumpulan
        holder.itemView.setOnClickListener{
            val dialog = BottomSheetDialog(holder.itemView.context)
            val sheetView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialogbuttonsheet, null)
            val matakuliah = sheetView.findViewById<TextView>(R.id.tv_nama_matakuliah)
            val semesterprodi = sheetView.findViewById<TextView>(R.id.tv_semester_prodi)
            val namauniversitas = sheetView.findViewById<TextView>(R.id.tv_nama_universitas)
            val des = sheetView.findViewById<TextView>(R.id.tv_deskripsi)
            val tglpengumpulan = sheetView.findViewById<TextView>(R.id.tv_pengumpulan)
            val back = sheetView.findViewById<Button>(R.id.btn_back)
            matakuliah.text = data.matakuliah
            semesterprodi.text = data.semester
            namauniversitas.text = data.universitas
            des.text = data.deskripsi
            tglpengumpulan.text = data.tanggal_pengumpulan
            back.setOnClickListener{
            }
            dialog.setContentView(sheetView)
//            dialog.setCancelable(false)
            dialog.show()
        }
        holder.binding.more.setOnClickListener{
            val popupMenu = PopupMenu(holder.itemView.context,it)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.updat -> {
                        val i = Intent(holder.itemView.context, Update::class.java)
                        i.putExtra("id", data.id)
                        i.putExtra("nama_matakuliah", data.matakuliah)
                        i.putExtra("semester", data.semester)
                        i.putExtra("nama_universitas", data.universitas)
                        i.putExtra("tanggal_tugas", data.tanggal_tugas)
                        i.putExtra("tanggal_pengumpulan", data.tanggal_pengumpulan)
                        i.putExtra("jam", data.watu_pengumpulan)
                        i.putExtra("deskripsi",data.deskripsi)
                        holder.itemView.context.startActivity(i)
                        true
                    }
                    R.id.delet -> {
//                        holder.listiner.onItemClick(data.id)
                        ApiRetrofitServices.endPoint.deletCatatan(data.id).enqueue(object :
                            Callback<ResponseCatatan> {
                            override fun onResponse(
                                call: Call<ResponseCatatan>,
                                response: Response<ResponseCatatan>
                            ) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        holder.itemView.context, "Delete TODO Success",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(holder.itemView.context, "Gagal", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<ResponseCatatan>, t: Throwable) {
                                Toast.makeText(holder.itemView.context, "$t", Toast.LENGTH_SHORT).show()
                            }

                        })
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.item_more)
            popupMenu.show()
        }
    }

    override fun getItemCount() = dataList.size

    fun setData(data: List<DataItem>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}