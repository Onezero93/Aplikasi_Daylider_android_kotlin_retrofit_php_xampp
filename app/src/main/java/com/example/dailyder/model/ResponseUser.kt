package com.example.dailyder.model

data class ResponseUser(
    val sukses: Boolean,
    val message: String,
    val id:String,
    var namauser: String,
    val nim: String,
    val universitas: String,
    val semester: String,
    val jenis_kelamin: String,
    val no_hp: String,
)

