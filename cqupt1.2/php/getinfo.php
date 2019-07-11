<?php
/**
 * Created by PhpStorm.
 * User: lenovo
 * Date: 2019/6/14
 * Time: 0:06
 * Function: 返回用户基本信息
 */
session_start();
include_once ("conn.php");
$id = $_SESSION['id'];
$result = mysqli_query($a,"SELECT username,password,email FROM USERINFO WHERE ID='".$id."'");
$arr = $result->fetch_assoc();
$rows = array("name" => $arr['username'],"pwd" => $arr['password'],"email" => $arr['email']);
echo json_encode($rows);
//var_dump($rows);