<?php
include('connection.php');
include('redis_connection.php');
$pat = "Select DISTINCT bucode, srf_number, queue_type, t1.queue_name, t2.count, time_added_to_queue from patient t1 inner join( SELECT queue_name, count(*) as count, min(time_added_to_queue) as earliest FROM patient Where queue_type != UPPER('NONE') and queue_name != UPPER('NONE') group by queue_name ) t2 on t1.time_added_to_queue = t2.earliest and t1.queue_name = t2.queue_name order by t1.queue_name";

/*********Start Searching in Redis**********************/
$data = array();
if (!$redis->get($pat)) {

	$source = 'MySQL Server';
	$stmt = $mysqli->prepare($pat);		
	$stmt->execute();		
	$result = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);		
	if($stmt->affected_rows>0) {
		
		foreach($result as $key => $val){
			if (!array_key_exists($val['queue_type'],$data))
			{	
				$data[$val['queue_type']]= array();
			}
			$data1 = array();
			$data1['total'] = $val['count'];
			$data1['queue_name'] = $val['queue_name'];
			$data1['bucode'] = $val['bucode'];	
			$data1['queue_type'] = $val['queue_type'];	
			$data1['time_added_to_queue'] = $val['time_added_to_queue'];

			array_push($data[$val['queue_type']],$data1);
		}
		$redis->set($pat, serialize($data));
		$redis->expire($pat, $timeout); // time 10 secand
		
	}
	$stmt->close();
}else{
	$source = 'Redis Server';
	$data = unserialize($redis->get($pat));
}	
/**********************End*******************************/ 
?>