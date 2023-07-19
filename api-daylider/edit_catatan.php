<?php
require_once('koneksi.php');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id_catatan'];
    $nama_matakuliah = $_POST['matkul'];
    $nama_universitas = $_POST['namauniversitas'];
    $semester_prodi = $_POST['semesterprodi'];
    $tgl_tugas = $_POST['tanggaltugas'];
    $tgl_pengumpulan = $_POST['tanggalpengumpulan'];
    $jam_pengumpulan = $_POST['watupengumpulan'];
    $deskripsi_tugas = $_POST['deskripsitugas'];
    $queri = "UPDATE tb_catatan SET matakuliah= '$nama_matakuliah',universitas= '$nama_universitas',semester= '$semester_prodi',
    tanggal_tugas= '$tgl_tugas',tanggal_pengumpulan= '$tgl_pengumpulan',watu_pengumpulan= '$jam_pengumpulan',deskripsi= '$deskripsi_tugas' WHERE id_catatan='$id'";
    $sql = mysqli_query($con, $queri);
    if ($sql) {
        echo json_encode(array('success' => true, 'message' => 'Data berhasil diubah'));
    } else {
        echo json_encode(array('success' => false, 'message' => 'Data gagal diubah'));
    }
} else {
    echo json_encode(array('success' => false, 'message' => 'Method yang digunakan keliru'));
}
