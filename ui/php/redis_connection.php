<?php
try {
    $timeout = 10;
    $source = '';
    $redis = new Redis();
    $redis->pconnect('127.0.0.1', 6379);
    //$redis->connect('127.0.0.1', 6379, 1, NULL, 100); // 1 sec timeout, 100ms delay between reconnection attempts.
    //$redis->auth('REDIS_PASSWORD');

    //$redis->pconnect('chbmsdev.redis.cache.windows.net', 6379); 	
    //$redis->auth('BgrLx4sZqfC+EH6mOWnb7z8B+qyDnlWSz7Uz6m4ztvU=');

   //echo "Connection to server sucessfully"; 
   //check whether server is running or not 
   //echo "Server is running: ".$redis->ping(); 

    
} catch (Exception $ex) {
    echo $ex->getMessage();
}

?>