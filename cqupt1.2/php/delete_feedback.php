<?php
/**
 * Created by PhpStorm.
 * User: lenovo
 * Date: 2019/6/13
 * Time: 21:12
 * Function: 删除系统消息
 */
session_start();
include_once ("conn.php");
$path0 = $_POST['src'];
preg_match('#view_delete/.+.jpg$#', $path0, $matches);
$path = './' . $matches[0];
$result = mysqli_query($a,"SELECT feedback , view_delete_path FROM USERINFO WHERE ID='".$id."'");
$arr1 = explode(",",$result->fetch_assoc()['feedback']);
$arr2 = explode(",",$result->fetch_assoc()['view_delete_path']);
$temp1 = [];
$temp2 = [];
for($i = 0;$i<count($arr2);$i++){
    if($path != $arr2[$i]) {
        $temp1[] = $arr1[$i];
        $temp2[] = $arr2[$i];
    }
}
if(count($temp1) > 1){
    $str1 = implode(",",$temp1);
    $str2 = implode(",",$temp2);
}
else if(count($temp1) == 1){
    $str1 = $temp1[0];
    $str2 = $temp2[0];
}
else{
    $str1 = NULL;
    $str2 = NULL;
}
$result = mysqli_query($a,"UPDATE USERINFO SET feedback='".$str1."', view_delete_path = '".$str2."' WHERE ID = '".$id."'");
$aff = mysqli_affected_rows($a);
$delete_path = ".".$path;
$bool = unlink($delete_path);
if($aff > 0 and $bool) echo json_encode(["status" => true]);
else echo json_encode(["status" => false]);