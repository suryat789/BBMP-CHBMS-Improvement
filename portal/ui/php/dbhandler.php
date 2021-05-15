<?php

include('connection.php');
/*
$key= "bed";
if (!$redis->get($key)) {
	$source = 'Mysql Server';
    $sql="select sum( occupied ) as total_occupied , sum( vacant ) as total_vacant, sum( capacity ) as total_capacity ,type as bedtype from bed  GROUP BY type";
    $result=mysqli_query($cn, $sql) or die(mysqli_error($cn));


    if(mysqli_num_rows($result)) {
      $data = array();
      $i = 0;
      while($row = mysqli_fetch_array($result))
        {
        $data[$i]['bed_capacity'] = $row['total_capacity'];
        $data[$i]['bed_occupied'] = $row['total_occupied'];
        $data[$i]['bed_vacant'] = $row['total_vacant'];
        $data[$i]['bed_bedtype'] = $row['bedtype'];
        $i++;
        }
    }


    $redis->set($key, serialize($data));
    $redis->expire($key, 10);

} else {
     $source = 'Redis Server';
     $data = unserialize($redis->get($key));

}
*/


	$source = 'Mysql Server';
    $sql="select sum( occupied ) as total_occupied , sum( vacant ) as total_vacant, sum( capacity ) as total_capacity ,type as bedtype from bed  GROUP BY type";
    $result=mysqli_query($cn, $sql) or die(mysqli_error($cn));


    if(mysqli_num_rows($result)) {
      $data = array();
      $i = 0;
      while($row = mysqli_fetch_array($result))
        {
        $data[$i]['bed_capacity'] = $row['total_capacity'];
        $data[$i]['bed_occupied'] = $row['total_occupied'];
        $data[$i]['bed_vacant'] = $row['total_vacant'];
        $data[$i]['bed_bedtype'] = $row['bedtype'];
        $i++;
        }
    }

?>
