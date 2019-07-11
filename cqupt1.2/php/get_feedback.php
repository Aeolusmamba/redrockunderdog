<?php
/**
 * Created by PhpStorm.
 * User: lenovo
 * Date: 2019/6/13
 * Time: 18:57
 * Function: 返回回馈信息
 */
session_start();
include_once("conn.php");
const NUM_PAGE = 5;
$page = $_POST['page'];
$id = $_SESSION['id'];
$result = mysqli_query($a,"SELECT feedback,view_delete_path FROM USERINFO WHERE ID='".$id."'");
$res_arr = $result->fetch_assoc();
if($res_arr['feedback'] != NULL) {    //有新消息
    $new_arr = explode(",", $res_arr['feedback']);
    $new_arr2 = explode(",", $res_arr['view_delete_path']);
    $temp = [];
    for ($i = ($page - 1) * NUM_PAGE; $i < $page * NUM_PAGE; $i++) {
        if ($i < count($new_arr)) {    //一一对应
            $temp[] = array("reason" => $new_arr[$i], "src" => $new_arr2[$i]);
        } else {
            $temp[] = array("reason" => "", "src" => "");
        }
    }
    echo json_encode($temp);
}
else{    //没有新消息
    $temp = [];
    for ($i = ($page - 1) * NUM_PAGE; $i < $page * NUM_PAGE; $i++) {
            $temp[] = array("reason" => "", "src" => "");
    }
    echo json_encode($temp);
}