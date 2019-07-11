<?php
/**
 * Created by PhpStorm.
 * User: lenovo
 * Date: 2019/6/13
 * Time: 21:15
 * Function: 判断是否有新消息
 */
session_start();
include_once ("conn.php");
$id = $_SESSION['id'];
$result=mysqli_query($a,"SELECT feedback FROM USERINFO WHERE ID ='".$id."'");
if($result->fetch_assoc()['feedback'] == NULL) echo json_encode(["status" => false]);
else echo json_encode(["status" => true]);