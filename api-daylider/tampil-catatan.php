<?php
require_once ('koneksi.php');
if($_SERVER['REQUEST_METHOD']=='GET'){
    $sql = "SELECT * FROM tb_catatan ORDER BY id_catatan DESC";
    $sql_tampil = mysqli_query($con,$sql);
    $hasil = array();
    if($sql_tampil){
        while($row = mysqli_fetch_array($sql_tampil)){
            array_push($hasil,array(
                'id' => $row['id_catatan'],
                'matakuliah' => $row['matakuliah'],
                'universitas' => $row['universitas'],
                'semester' => $row['semester'],
                'tanggal_tugas' => $row['tanggal_tugas'],
                'tanggal_pengumpulan' => $row['tanggal_pengumpulan'],
                'watu_pengumpulan' => $row['watu_pengumpulan'],
                'deskripsi' =>$row['deskripsi'],

            ));
        }
        echo json_encode(
            array(
                'sukses'=>true,
                'message'=> 'Daftar Catatan Daylider',
                'data'=> $hasil
            )
        );
    }else{
        echo json_encode(
            array(
                'sukses'=>false,
                'message'=> 'Gagal mengeksekusi query tampil',
                )
            );
    }
}else{
    echo json_encode(
        array(
            'sukses'=>false,
            'message'=> 'Method yang di gunakan keliru',
            )
        );
    }
