<?php 
include('connection.php');   
$query1="SELECT * FROM `patient` WHERE zone='ZONE 1'";
	$stmt = $mysqli->prepare($query1);		
	$stmt->execute();		
	$queueResult = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);		
	if($stmt->affected_rows>0) {       
	 foreach($queueResult as $val){			
            $bucode1 = $val["bucode"];
            $id1 = $val["id"];
            $srfnumber1 = $val["srf_number"];
			$time1 = $val["time_added_to_queue"];
	 }
	}

	//echo '<pre>';
	//print_r($queueResult);exit;
	$stmt->close();

?>



