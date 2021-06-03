<?php 
include('connection.php');
$pagetype = urldecode($_GET['patientpage']);
## Read value
if($pagetype=="")
{
  $pagetype="Zone 1";
}
$draw = $_POST['draw'];
$row = $_POST['start'];
$rowperpage = $_POST['length']; // Rows display per page
$columnIndex = $_POST['order'][0]['column']; // Column index
$columnName = $_POST['columns'][$columnIndex]['data']; // Column name
$columnSortOrder = $_POST['order'][0]['dir']; // asc or desc
//$searchValue = mysqli_real_escape_string($cn,$_POST['search']['value']); // Search value
$searchValue = $_POST['search']['value']; // Search value


## Search 
$searchQuery = " ";
if($searchValue != ''){
   $searchQuery = " and (id like '%".$searchValue."%' or 
        bucode like '%".$searchValue."%' or 
        srf_number like'%".$searchValue."%' ) ";
}

## Total number of records without filtering
    $selWithoutFilter = "select count(*) as allcount from patient where queue_name= ? ";    
    $stmt = $mysqli->prepare($selWithoutFilter);
    $stmt->bind_param("s", $pagetype);
    //echo $mysqli->query($selWithoutFilter,$resultmode);	

		$stmt->execute();		
		$resulWithoutFilter = $stmt->get_result()->fetch_row()[0];
    //echo $mysqli->query($selWithoutFilter,$resultmode);	
    $totalRecords = $resulWithoutFilter;		
		$stmt->close();

## Total number of record with filtering
$selWithFilter= "select count(*) as allcount from patient where queue_name= ? ".$searchQuery;
$stmt = $mysqli->prepare($selWithFilter);	
$stmt->bind_param("s", $pagetype);
$stmt->execute();		
$resultwithFilter = $stmt->get_result()->fetch_row()[0];
$totalRecordwithFilter = $resultwithFilter;		
$stmt->close();

$columnName = "time_added_to_queue";
$columnSortOrder = "asc";


if($row=="")
{
  $row = 0;
}
if($rowperpage=="")
{
$rowperpage = 10;
}

## Fetch records

 $empQuery = "select  bucode, srf_number,patient_id,time_added_to_queue from patient where queue_name = ? ".$searchQuery ." order by ".$columnName." ".$columnSortOrder." limit ".$row.",".$rowperpage;
 	
 $stmt = $mysqli->prepare($empQuery);
  $stmt->bind_param("s", $pagetype);		
		$stmt->execute();	

		$result = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);	
    $data = array();    
      $i = $row + 1; 
    
		if($stmt->affected_rows>0) {
     
		 foreach($result as $key => $val){
				$data[$key]['patient_id'] = $i;
				$data[$key]['bucode'] = $val['bucode'];
        $data[$key]['srf_number'] = $val['srf_number'];	
        $data[$key]['time_added_to_queue'] = date('d/m/Y h:i:s A', strtotime($val['time_added_to_queue']));	
        $i++;
		 }
		}

		$stmt->close();
   
    
## Response
$response = array(
  "draw" => intval($draw),
  "iTotalRecords" => $totalRecords,
  "iTotalDisplayRecords" => $totalRecordwithFilter,
  "aaData" => $data
);

echo json_encode($response);
?>