<?php
   include('connection.php'); 
		$pat ="Select DISTINCT bucode, srf_number, queue_type, t1.queue_name, t2.count, time_added_to_queue from patient t1 inner join( SELECT queue_name, count(*) as count, min(time_added_to_queue) as earliest FROM patient group by queue_name ) t2 on t1.time_added_to_queue = t2.earliest";
		$stmt = $mysqli->prepare($pat);		
		$stmt->execute();		
		$result = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);		
		if($stmt->affected_rows>0) {
			$pdata = array();		
			foreach($result as $key => $val){
					$pdata[$key]['total'] = $val['count'];
					$pdata[$key]['queue_name'] = $val['queue_name'];
					$pdata[$key]['bucode'] = $val['bucode'];	
					$pdata[$key]['queue_type'] = $val['queue_type'];	
					$pdata[$key]['time_added_to_queue'] = $val['time_added_to_queue'];
			}
		}
		$stmt->close();
		
?>