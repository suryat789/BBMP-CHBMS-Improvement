<?php
if($_SERVER['HTTP_HOST'] == 'localhost')
{
   //echo "Connection to server sucessfully"; 

   //Connecting to Redis server on localhost 
  // $redis = new Redis(); 

   //$redis->connect('127.0.0.1', 6379); 
   //echo "Connection to server sucessfully"; 

         //check whether server is running or not 
   //echo "Server is running: ".$redis->ping(); 

	$dbservertype='mysqli';
	$servername='localhost';
	$dbuser='root';
	$dbpassword='root';
	$dbname='chbms';

}
else {
// Live Db connetion
   //Connecting to Redis server on localhost 
/*   $redis = new Redis(); 
   $redis->connect('chbmsdev.redis.cache.windows.net', 6380); 	
   $redis->auth('xConUgEAPAngs6BsCG1Uch7t8qZKwCzZYHRwoMnBalQ=');

     echo "Connection to server sucessfully"; 

         //check whether server is running or not 
     echo "Server is running: ".$redis->ping(); */

	$dbservertype='mysqli';
	$servername='chbms-db.mysql.database.azure.com';
	$dbuser='dbadmin@chbms-db';
	$dbpassword='eGovCa$hc0w';
	$dbname='chbms'; 


}

//echo $dbname;

$cn = mysqli_connect($servername,$dbuser,$dbpassword,$dbname);
global $cn;

// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  exit();
}

?>