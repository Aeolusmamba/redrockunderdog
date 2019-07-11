<?php
/**
 * Created by PhpStorm.
 * User: lenovo
 * Date: 2019/6/13
 * Time: 23:45
 * Function: 返回轮播的上传图片
 */
session_start();
include_once("conn.php");
$id = $_SESSION['id'];
$result = mysqli_query($a, "SELECT uploading FROM USERINFO WHERE ID='" . $id . "'");
if ($result->fetch_assoc()['uploading'] != NULL) {   //有上传记录
    $arr = explode(",", $result->fetch_assoc()['uploading']);
    $key = [];
    for ($i = 0; $i < count($arr); $i++) $key[] = "src";
    echo json_encode(array_combine($key, $arr));
} else echo json_encode(["status" => false]);