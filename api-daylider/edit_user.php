<?php
    require_once('koneksi.php');
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $id = $_POST['id_user'];
        $user = $_POST['namauser'];
        $nim = $_POST['nim'];
        $nama_universitas = $_POST['namauniversitas'];
        $semester_prodi = $_POST['semesterprodi'];
        $jenis = $_POST['jeniskelamin'];
        $handpon = $_POST['nohp'];
        $queri = "UPDATE tb_user SET nama_user= '$user',nim= '$nim',universitas= '$nama_universitas',
    semester= '$semester_prodi',jenis_kelamin= '$jenis',no_hp= '$handpon' WHERE id_user='$id'";
    $sql = mysqli_query($con, $queri);
    if ($sql) {
        echo json_encode(array('success' => true, 'message' => 'Data berhasil diubah'));
    } else {
        echo json_encode(array('success' => false, 'message' => 'Data gagal diubah'));
    }
} else {
    echo json_encode(array('success' => false, 'message' => 'Method yang digunakan keliru'));
}