package com.example.dailyder.model

import java.io.Serializable


data class ResponseCatatan(

	val data: List<DataItem>,
	val sukses: Boolean,
	val message: String
)

data class DataItem(
	val universitas: String,
	val semester: String,
	val tanggal_pengumpulan: String,
	val deskripsi: String,
	val tanggal_tugas: String,
	val watu_pengumpulan: String,
	val id: String,
	val matakuliah: String
): Serializable
