 <?php
    require_once('koneksi.php');
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $nama_matakuliah = $_POST['matkul'];
        $nama_universitas = $_POST['namauniversitas'];
        $semester_prodi = $_POST['semesterprodi'];
        $tgl_tugas = $_POST['tanggaltugas'];
        $tgl_pengumpulan = $_POST['tanggalpengumpulan'];
        $jam_pengumpulan = $_POST['watupengumpulan'];
        $deskripsi_tugas = $_POST['deskripsitugas'];
        $query = "INSERT INTO tb_catatan (matakuliah,universitas,semester,tanggal_tugas,tanggal_pengumpulan,watu_pengumpulan,deskripsi)VALUES('$nama_matakuliah','$nama_universitas','$semester_prodi','$tgl_tugas','$tgl_pengumpulan','$jam_pengumpulan','$deskripsi_tugas')";
        $sql = mysqli_query($con, $query);
        if ($sql) {
            echo json_encode(
                array(
                    'sukses' => true,
                    'message' => 'Catatan Berhasil ditambah',
                )
            );
        } else {
            echo json_encode(
                array(
                    'sukses' => false,
                    'message' => 'Catatan gagal di tambah',
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
    ?> 