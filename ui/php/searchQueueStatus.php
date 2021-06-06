<?php
 header('Content-type: application/json');
   include('connection.php');  
    $buCode = $_POST['buCode'];
    $phone = $_POST['phone'];   
    $query1="select p1.id, p1.bucode,p1.queue_name,p1.queue_type,p1.srf_number,p1.zone, (SELECT count(*) FROM patient p2 where p2.queue_name = p1.queue_name and p2.queue_type = p1.queue_type and p2.time_added_to_queue < p1.time_added_to_queue) AS queue_position from patient p1 where p1.bucode = ? || p1.srf_number = ? and p1.phone = ? ";	
    $query = $mysqli->prepare($query1);
    $query->bind_param('sss', $buCode,$buCode,$phone);
    $query->execute();
    $data = array(); 
    if ($result = $query->get_result()) {   
                 
        if ($result->num_rows > 0) {
                      
            while($row = $result->fetch_assoc()) 
            {
                $data['id'] = trim($row["id"]);
                $data['bucode'] = trim($row["bucode"]);                
                $data['srfnumber'] = trim($row["srf_number"]);
                $data['queue_type'] = trim($row["queue_type"]);
                $data['queue_position'] = trim($row["queue_position"])+1; 
                $data['queue_name'] = trim($row["queue_name"]);
                $data['zone'] = trim($row["zone"]);                        
            }
        }
    } 
    echo json_encode($data);
        
  
?>