<?php 
include('connection.php');
$pagetype = $_GET['bedtypepage'];
## Read value
if($pagetype=="")
{
  $pagetype="General";
}
$draw = $_POST['draw'];
$row = $_POST['start'];
$rowperpage = $_POST['length']; // Rows display per page
$columnIndex = $_POST['order'][0]['column']; // Column index
$columnName = $_POST['columns'][$columnIndex]['data']; // Column name
$columnSortOrder = $_POST['order'][0]['dir']; // asc or desc
$searchValue = $_POST['search']['value']; // Search value

## Search 
$searchQuery = " ";
if($searchValue != ''){
   $searchQuery = " and (c2.name like '%".$searchValue."%' or 
        c2.type like '%".$searchValue."%' or 
        c2.phone like'%".$searchValue."%' or 
        c2.updated_on like'%".$searchValue."%' ) ";
}
## Total number of records without filtering
$selWithoutFilter = "select count(*) as allcount from bed as c1,hospital as c2 where c1.type = ? and c1.hospital_id = c2.id";
$stmt = $mysqli->prepare($selWithoutFilter);
$stmt->bind_param("s", $pagetype);  
$stmt->execute();		
$resulWithoutFilter = $stmt->get_result()->fetch_row()[0];
//echo $mysqli->query($selWithoutFilter,$resultmode);	
$totalRecords = $resulWithoutFilter;		
$stmt->close();



## Total number of record with filtering
$selWithFilter= "select count(*) as allcount from bed as c1,hospital as c2 where c1.type = ? and c1.hospital_id = c2.id ".$searchQuery;
$stmt = $mysqli->prepare($selWithFilter);	
$stmt->bind_param("s", $pagetype);
$stmt->execute();		
$resultwithFilter = $stmt->get_result()->fetch_row()[0];
$totalRecordwithFilter = $resultwithFilter;		
$stmt->close();



if($columnName=="")
{
  $columnName = "c2.name";

}
if($columnSortOrder=="")
{
$columnSortOrder = "asc";
}

if($row=="")
{
  $row = 0;
}
if($rowperpage=="")
{
$rowperpage = 10;
}

## Fetch records
$empQuery = "select c1.capacity,c1.occupied,c1.vacant,c1.updated_on,c2.name,c2.type,c2.phone from bed as c1,hospital
 as  c2 where c1.type = ?  and c1.hospital_id = c2.id ".$searchQuery." order by ".$columnName." ".$columnSortOrder." limit ".$row.",".$rowperpage;
 	
 $stmt = $mysqli->prepare($empQuery);
  $stmt->bind_param("s", $pagetype);		
		$stmt->execute();
		$result = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);	
    	
		if($stmt->affected_rows>0) {
			$data = array();	
		 foreach($result as $key => $val){
				$data[$key]['name'] = $val['name'];
				$data[$key]['vacant'] = $val['vacant'];
        $data[$key]['capacity'] = $val['capacity'];	
        $data[$key]['occupied'] = $val['occupied'];	
        $data[$key]['type'] = $val['type'];
        $data[$key]['phone'] = $val['phone'];	
        $data[$key]['updated_on'] = date('d/m/Y h:i A', strtotime($val['updated_on']));		
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