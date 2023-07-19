<?php
require_once ('koneksi.php');
if($_SERVER['REQUEST_METHOD']=='GET'){
    $sql = "SELECT * FROM tb_semester ORDER BY id_semester DESC";
    $sql_tampil = mysqli_query($con,$sql);
    $hasil = array();
    if($sql_tampil){
        while($row = mysqli_fetch_array($sql_tampil)){
            array_push($hasil,array(
                'id' => $row['id_semester'],
                'semester' => $row['semester'],
                

            ));
        }
        echo json_encode(
            array(
                'sukses'=>true,
                'message'=> 'Daftar Semester Universitas',
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
