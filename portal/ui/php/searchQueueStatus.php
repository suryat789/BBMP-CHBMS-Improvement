<?php
// header('Content-type: application/json');
   include('connection.php'); 
  
    $buCode = $_POST['buCode'];
    $phone = $_POST['phone'];
    $query1="select id, INSERT(bucode, 3, 4, '****') as bucode, queue_name ,zone, srf_number, queue_type, min(time_added_to_queue) as earliest FROM `patient` WHERE bucode = ? and phone = ?";
	
    $query = $mysqli->prepare($query1);
    $query->bind_param('ss', $buCode,$phone);
    $query->execute();
    if ($result = $query->get_result()) {              
        if ($result->num_rows > 0) {
            $data = array();
            
            while($row = $result->fetch_assoc()) 
            {
                $data['bucode'] = $row["bucode"];
                $data['id'] = $row["id"];
                $data['srfnumber'] = $row["srf_number"];
                $data['queue_type'] = $row["queue_type"];
                $data['earliest'] = $row["earliest"]; 
                $data['queue_name'] = $row["queue_name"];
                $data['zone'] = $row["zone"];                        
            }
        }
    }
    

    
    $jsonData = json_encode($data);
    echo $jsonData;
    exit;
?>