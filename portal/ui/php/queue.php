<?php 
include('connection.php');
$query1="SELECT * FROM `patient` WHERE zone='ZONE 1'";
$result1=mysqli_query($cn,$query1);
/*print_r($result1) ; */
while ($row1 = $result1->fetch_assoc()) {
    $bucode1 = $row1["bucode"];
    $id1 = $row1["id"];
    $srfnumber1 = $row1["srf_number"];
    $time1 = $row1["time_added_to_queue"];
       
}


?>



