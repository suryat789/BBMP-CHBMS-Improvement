<?php 
    $zoneTyp1= 'ZONE 1';
    $zoneTyp2= 'ZONE 2';
    $query1 = "SELECT COUNT(*) FROM `patient` WHERE zone = ?";    
    $stmt = $mysqli->prepare($query1);
    $stmt->bind_param("s", $zoneTyp1);  
	$stmt->execute();		
	$result = $stmt->get_result()->fetch_row()[0];
    //echo $mysqli->query($selWithoutFilter,$resultmode);	
    $zone1 = $result;		
	$stmt->close();

    $query2= "SELECT COUNT(*) FROM `patient` WHERE zone = ?";    
    $stmt = $mysqli->prepare($query2);
    $stmt->bind_param("s", $zoneTyp2);  
	$stmt->execute();		
	$result2 = $stmt->get_result()->fetch_row()[0];
    //echo $mysqli->query($selWithoutFilter,$resultmode);	
    $zone2 = $result2;		
	$stmt->close();

?>