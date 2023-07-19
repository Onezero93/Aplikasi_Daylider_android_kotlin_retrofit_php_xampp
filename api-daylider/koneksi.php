<?php
define('HOST', 'localhost');
define('USER', 'root');
define('PASS', '');
define('DB', 'db_daylider');

$con = mysqli_connect(HOST, USER, PASS, DB) or die('Unable to Connect');
header('Content-Type: application/json');
?>