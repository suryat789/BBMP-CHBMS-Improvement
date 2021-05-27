<?php
 header('Content-type: application/json');
   include('connection.php');
   include('redis_connection.php');

    $buCode = $_POST['buCode'];
    $phone = $_POST['phone'];

    
    #Function to print prepared statement query`
    function SqlDebug($raw_sql, $params=array())
      {
      $keys = array();
      $values = $params;  
      foreach ($params as $key => $value)
      {
        // check if named parameters (':param') or anonymous parameters ('?') are    used
        if (is_string($key)) { $keys[] = '/:'.$key.'/'; }
        else                 { $keys[] = '/[?]/'; }
        // bring parameter into human-readable format
        if (is_string($value))    { $values[$key] = "'" . $value . "'"; }
        elseif (is_array($value)) { $values[$key] = implode(',', $value); }
        elseif (is_null($value))  { $values[$key] = 'NULL'; }
      }
      $raw_sql = preg_replace($keys, $values, $raw_sql, 1, $count);
      return $raw_sql;
      }
    $data = array();   
    $query1="select p1.id, p1.bucode,p1.queue_name,p1.queue_type,p1.srf_number,p1.zone, (SELECT count(*) FROM patient p2 where p2.queue_name = p1.queue_name and p2.queue_type = p1.queue_type and p2.time_added_to_queue < p1.time_added_to_queue) AS queue_position from patient p1 where p1.bucode = ? and p1.phone = ? ";	
    if (!$redis->get($query1)) {  
    
            $query = $mysqli->prepare($query1);
            $query->bind_param('ss', $buCode,$phone);
            
            $query->execute();
            //echo "contenuto: " .SqlDebug($query1, array($buCode,$phone)); 
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
                    $redis->set($query1, serialize($data));
		            $redis->expire($query1, $timeout); // time 10 secand
                }
            }
    }else{
        $source = 'Redis Server';
	    $data = unserialize($redis->get($query1));

    }    
            
    echo json_encode($data);
    
  
?>