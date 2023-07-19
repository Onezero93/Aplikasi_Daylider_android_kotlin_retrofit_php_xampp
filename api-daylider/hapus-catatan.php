<?php
require_once('koneksi.php');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['idcatatan'];

    $queri = "DELETE FROM tb_catatan WHERE id_catatan = '$id' ";
    $sql = mysqli_query($con, $queri);

    if ($sql) {
        echo json_encode(array('success' => true, 'message' => 'Catatan berhasil dihapus'));
    } else {
        echo json_encode(array('success' => false, 'message' => 'Catatan gagal dihapus'));
    }
} else {
    echo json_encode(array('success' => false, 'message' => 'Method yang digunakan keliru'));
}
?>