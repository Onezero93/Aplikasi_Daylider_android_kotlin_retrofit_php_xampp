<?php
require_once('koneksi.php');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $user = $_POST['namauser'];
    $password = $_POST['password'];
    $query = "SELECT * FROM tb_user where nama_user = '$user' AND password_user = '$password'";
    $hasil = array();
    $result = mysqli_query($con, $query);
    if ($result && mysqli_num_rows($result) > 0) {
        $user_data = mysqli_fetch_assoc($result);
        $hashed_password = $user_data['password_user'];

        if ($result) {
            echo json_encode(
                array(
                    'sukses' => true,
                    'message' => 'Welcom',
                )
            );
        } else {
            echo json_encode(
                array(
                    'sukses' => false,
                    'message' => 'Password salah',
                )
            );
        }
    } else {
        echo json_encode(
            array(
                'sukses' => false,
                'message' => 'User tidak ditemukan',
            )
        );
    }
} else {
    echo json_encode(
        array(
            'sukses' => false,
            'message' => 'Method yang digunakan salah',
        )
    );
}
?>