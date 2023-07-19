package com.example.dailyder.network


import com.example.dailyder.model.ResponseCatatan
import com.example.dailyder.model.ResponseSemester
import com.example.dailyder.model.ResponseUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {
//untuk catatan
    @GET("tampil-catatan.php")
    fun getCatatan(): Call<ResponseCatatan>

    @GET("tampil-semester.php")
    fun getSemester(): Call<ResponseSemester>

    @FormUrlEncoded
    @POST("simpan-catatan.php")
    fun postCatatan(
        @Field("matkul") matkul: String,
        @Field("namauniversitas") universitas: String,
        @Field("semesterprodi") semester: String,
        @Field("tanggaltugas") tanggal_tugas: String,
        @Field("tanggalpengumpulan") tanggal_pengumpulan: String,
        @Field("watupengumpulan") watu_pengumpulan: String,
        @Field("deskripsitugas") deskripsi: String,
    ): Call<ResponseCatatan>

    @FormUrlEncoded
    @POST("edit_catatan.php")
    fun postEdit(
        @Field("id_catatan") tugas_id: String,
        @Field("matkul") matkul: String,
        @Field("namauniversitas") universitas: String,
        @Field("semesterprodi") semester: String,
        @Field("tanggaltugas") tanggal_tugas: String,
        @Field("tanggalpengumpulan") tanggal_pengumpulan: String,
        @Field("watupengumpulan") watu_pengumpulan: String,
        @Field("deskripsitugas") deskripsi: String,
    ): Call<ResponseCatatan>

    @FormUrlEncoded
    @POST("hapus-catatan.php")
    fun deletCatatan(
        @Field("idcatatan") idcatatan: String,
    ): Call<ResponseCatatan>

    //untuk user

    @GET("tampil_user.php")
    fun getUser(
        @Field("id_user") id_user: String
    ): Call<ResponseUser>
    @FormUrlEncoded
    @POST("edit_user.php")
    fun postUser(
//        @Field("id_user") user_id: String,
        @Field("namauser") namauser: String,
        @Field("nim") nim: String,
        @Field("namauniversitas") namauniversitas: String,
        @Field("semesterprodi") semesterprodin: String,
        @Field("jeniskelamin") jeniskelamin: String,
        @Field("nohp") nohp: String,
    ): Call<ResponseUser>

    @FormUrlEncoded
    @POST("registrasi_user.php")
    fun postRegistrasi(
        @Field("namauser") namauser: String,
        @Field("namauniversitas") namauniversitas: String,
        @Field("password") password: String
    ): Call<ResponseUser>

    @FormUrlEncoded
    @POST("login_user.php")
    fun login(
        @Field("namauser") username: String,
        @Field("password") password: String
    ): Call<ResponseUser>
}