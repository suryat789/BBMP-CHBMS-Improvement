<?php 
include('connection.php');
$pagetype = $_GET['bedpage'];
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
$searchValue = mysqli_real_escape_string($cn,$_POST['search']['value']); // Search value

## Search 
$searchQuery = " ";
if($searchValue != ''){
   $searchQuery = " and (id like '%".$searchValue."%' or 
        bucode like '%".$searchValue."%' or 
        srf_number like'%".$searchValue."%' ) ";
}
## Total number of records without filtering
$sel = mysqli_query($cn,"select count(*) as allcount from patient where queue_name='".$pagetype ."' ");
$records = mysqli_fetch_assoc($sel);
$totalRecords = $records['allcount'];



## Total number of record with filtering
$sel = mysqli_query($cn,"select count(*) as allcount from patient where queue_name='".$pagetype ."' ".$searchQuery);
$records = mysqli_fetch_assoc($sel);
$totalRecordwithFilter = $records['allcount'];



if($columnName=="")
{
  $columnName = "bucode";

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
$empQuery = "select * from patient where queue_name='".$pagetype ."' ".$searchQuery ." order by ".$columnName." ".$columnSortOrder." limit ".$row.",".$rowperpage;
$empRecords = mysqli_query($cn, $empQuery);
$data = array();


while ($row = mysqli_fetch_assoc($empRecords)) {
   $data[] = array( 
      "patient_id"=>$row['patient_id'],
      "bucode"=>$row['bucode'],
      "srf_number"=>$row['srf_number'],
      "time_added_to_queue"=>$row['time_added_to_queue'],
   );
}



## Response
$response = array(
  "draw" => intval($draw),
  "iTotalRecords" => $totalRecords,
  "iTotalDisplayRecords" => $totalRecordwithFilter,
  "aaData" => $data
);

echo json_encode($response);
?>