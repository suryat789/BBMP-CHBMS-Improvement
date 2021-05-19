<?php
   include('connection.php'); 
  
    $buCode = $_POST['buCode'];
    $phone = $_POST['phone'];
    $query1="select id, INSERT(bucode, 3, 4, '****') as bucode, queue_name ,zone, srf_number, queue_type, min(time_added_to_queue) as earliest FROM `patient` WHERE bucode = ? and phone = ?";
	$stmt = $mysqli->prepare($query1);	
    $stmt->bind_param("ss", $buCode,$phone);
    //$stmt->bind_param("s", $phone);	
	$stmt->execute();		
	$queueResult = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);
    //echo $mysqli->query($queueResult);
    $data= array();		
	if($stmt->affected_rows>0) {       
	 foreach($queueResult as $val){			
        $data['bucode'] = $val["bucode"];
        $data['id'] = $val["id"];
        $data['srfnumber'] = $val["srf_number"];
        $data['queue_type'] = $val["queue_type"];
        $data['earliest'] = $val["earliest"]; 
        $data['queue_name'] = $val["queue_name"];
        $data['zone'] = $val["zone"];           
	 }
	}
    
//print_r($data);
//exit;

		$stmt->close();
        echo json_encode($data);
?>