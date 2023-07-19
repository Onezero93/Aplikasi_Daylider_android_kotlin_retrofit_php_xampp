<?php
require_once('koneksi.php');
if($_SERVER['REQUEST_METHOD']=='GET'){
    $id = $_GET['id_user'];
    $sql = "SELECT * FROM tb_user WHERE id_user = '$id' ORDER BY id_user DESC";
    $sql_tampil = mysqli_query($con,$sql);
    $hasil = array();
    if($sql_tampil){
        while($row = mysqli_fetch_array($sql_tampil)){
            array_push($hasil,array(
                'id' => $row['id_user'],
                'namauser' => $row['nama_user'],
                'nim' => $row['nim'],
                'namauniversitas' => $row['universitas'],
                'semesterprodi' =>$row['semester'],
                'jeniskelamin' =>$row['jenis_kelamin'],
                'nohp' =>$row['no_hp'],
            ));
        }
        echo json_encode(
            array(
                'sukses'=>true,
                'message'=> 'Daftar Todolist',
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
?>