<?php

include('connection.php');
include('redis_connection.php');
	
 $sql="select sum( occupied ) as total_occupied,sum( blocked ) as total_blocked  , sum( vacant ) as total_vacant, sum( capacity ) as total_capacity ,type as bedtype from bed Where occupied IS NOT NULL AND blocked IS NOT NULL AND vacant IS NOT NULL AND capacity IS NOT NULL AND bed_id IS NOT NULL GROUP BY bedtype";
/*********Start Searching in Redis**********************/
$data = array();
if (!$redis->get($sql)) {
  //echo $source = 'MySQL Server';
    if ($result = $mysqli->query($sql)) {
      if ($result->num_rows > 0) {
       
        $i = 0;
        while($row = $result->fetch_assoc()) 
        {
          $data[$i]['bed_capacity'] = $row['total_capacity'];
          $data[$i]['bed_occupied'] = $row['total_occupied'];
          $data[$i]['bed_vacant'] = $row['total_vacant'];
          $data[$i]['bed_bedtype'] = $row['bedtype'];
          $data[$i]['bed_blocked'] = $row['total_blocked'];
          $i++;
        }
        $redis->set($sql, serialize($data));
		    $redis->expire($sql, $timeout); // time 10 secand
      }
    }
  }else{
    //echo $source = 'Redis Server';
    $data = unserialize($redis->get($sql));
  }	
  /**********************End*******************************/  
  

function bedprogress($bedtype="",$seat="")
{
  global $mysqli;  
      $query = $mysqli->prepare("select sum( ".$seat." ) as total_".$seat." from bed  where type = ? and updated_on BETWEEN CURDATE() - INTERVAL 1 DAY
        AND CURDATE() - INTERVAL 1 SECOND;");
		$query->bind_param('s', $bedtype);
		$query->execute();
		if ($result = $query->get_result()) {			
      if ($result->num_rows > 0) {
        $data = array();
        $i = 0;
        while($row = $result->fetch_assoc()) 
        {
          return $row['total_'.$seat];
        }
      }
	}
}
?>
