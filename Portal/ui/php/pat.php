<?php

        $sql="Select INSERT(bucode, 3, 4, '****') as bucode, srf_number, queue_type, t1.queue_name, t2.count, time_added_to_queue from chbms.patient t1 inner join( SELECT queue_name, count(*) as count, min(time_added_to_queue) as earliest FROM chbms.patient WHERE queue_type='triage' group by queue_name  ) t2 on t1.time_added_to_queue = t2.earliest;";
		$result=mysqli_query($cn, $sql) or die(mysqli_error($cn));

		if(mysqli_num_rows($result)) {
			$data = array();
			$i = 0;
			while($row = mysqli_fetch_array($result))
				{
				$data[$i]['total'] = $row['count'];
				$data[$i]['queue_name'] = $row['queue_name'];
                $data[$i]['bucode'] = $row['bucode'];
				$i++;
				}
		}
	
	
	

?>