<?php
ini_set('display_errors', 1);
// Live Db connetion
   //Connecting to Redis server on localhost 
   $redis = new Redis(); 
   $redis->connect('chbms.redis.cache.windows.net', 6379);   
   $redis->auth('BgrLx4sZqfC+EH6mOWnb7z8B+qyDnlWSz7Uz6m4ztvU=');

     echo "Connection to server sucessfully"; 

         //check whether server is running or not 
     echo "Server is running: ".$redis->ping(); 

/*echo "hello";
    $redis=new Redis();
    $connected= $redis->connect('chbmsdev.redis.cache.windows.net', 6380);
    if(!$connected) {
        // some other code to handle connection problem
        die( "Cannot connect to redis server.\n" );
    }
    $redis->setex('somekey', 60, 'some value');
     echo "Server is running: ".$redis->ping(); */*/
    exit;

include('php/connection.php');

$key= "bed";
if (!$redis->get($key)) {
      $source = 'MySQL Server';
      $dbservertype='mysqli';
      $servername='localhost';
      $dbuser='root';
      $dbpassword='root';
      $dbname='chbms';

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

echo $source . ': <br>';
print_r($data);

?>