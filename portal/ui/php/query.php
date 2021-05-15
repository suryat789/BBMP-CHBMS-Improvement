<?php 
$query1="SELECT COUNT(*) FROM `patient` WHERE zone='ZONE 1'";
$result1=mysqli_query($cn,$query1);
$zone1 = mysqli_fetch_array($result1, MYSQLI_NUM);
$query2="SELECT COUNT(*) FROM `patient` WHERE zone='ZONE 2'";
$result2=mysqli_query($cn,$query2);
$zone2 = mysqli_fetch_array($result2, MYSQLI_NUM);
?>