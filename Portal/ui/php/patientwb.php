<?php
   include('connection.php');
		$pat="Select INSERT(bucode, 3, 4, '****') as bucode, srf_number, queue_type, t1.queue_name, t2.count, time_added_to_queue from chbms.patient t1 inner join( SELECT queue_name, count(*) as count, min(time_added_to_queue) as earliest FROM chbms.patient WHERE queue_type='bed' group by queue_name  ) t2 on t1.time_added_to_queue = t2.earliest;";
		$presult=mysqli_query($cn, $pat) or die(mysqli_error($cn));

		if(mysqli_num_rows($presult)) {
			$pdata = array();
			$j = 0;
			while($prow = mysqli_fetch_array($presult))
				{
				$pdata[$j]['total'] = $prow['count'];
				$pdata[$j]['queue_name'] = $prow['queue_name'];
                $pdata[$j]['bucode'] = $prow['bucode'];
				$j++;
				}
		}
	 

	

?>