<?php
/* ======== Memcached Server Connection ========= */
$memcache = new Memcache;
$memcache->addServer('localhost', 11211) or die ("Could not connect");
/* ======== Memcached Server Connection ========= */

?>