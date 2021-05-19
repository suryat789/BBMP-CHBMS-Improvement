<?php
date_default_timezone_set('Asia/Kolkata');
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

//$cn = mysqli_connect($servername,$dbuser,$dbpassword,$dbname);
global $mysqli;

//mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
try {
  $mysqli = new mysqli($servername, $dbuser, $dbpassword, $dbname);
  $mysqli->set_charset("utf8mb4");
} catch(Exception $e) {
  error_log($e->getMessage());
  exit('Error connecting to database'); //Should be a message a typical user could understand
}
// Check connection
/*if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  exit();
}*/

?>