<?php
    require_once('koneksi.php');
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $user = $_POST['namauser'];
        $nama_universitas = $_POST['namauniversitas'];
        $password = $_POST['password'];
        $query = "INSERT INTO  tb_user (nama_user,universitas,password_user) VALUES('$user','$nama_universitas','$password')";
        $sql = mysqli_query($con, $query);
        if ($sql) {
            echo json_encode(
                array(
                    'sukses' => true,
                    'message' => 'Registrasi Berhasil',
                )
            );
        } else {
            echo json_encode(
                array(
                    'sukses' => false,
                    'message' => 'Registrasi gagal',
                )
            );
        }
    } else {
        echo json_encode(
            array(
                'sukses' => false,
                'message' => 'Method yang di gunakan keliru',
            )
        );
    }
