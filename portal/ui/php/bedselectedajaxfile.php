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
$searchValue = mysqli_real_escape_string($cn,$_POST['search']['value']); // Search value

## Search 
$searchQuery = " ";
if($searchValue != ''){
   $searchQuery = " and (c2.name like '%".$searchValue."%' or 
        c2.type like '%".$searchValue."%' or 
        c2.phone like'%".$searchValue."%' or 
        c2.updated_on like'%".$searchValue."%' ) ";
}
## Total number of records without filtering
$sel = mysqli_query($cn,"select count(*) as allcount from bed as c1,hospital as c2 where c1.type='".$pagetype ."' and c1.hospital_id = c2.id");
$records = mysqli_fetch_assoc($sel);
$totalRecords = $records['allcount'];



## Total number of record with filtering
$sel = mysqli_query($cn,"select count(*) as allcount from bed as c1,hospital as c2 where c1.type='".$pagetype ."' and c1.hospital_id = c2.id ".$searchQuery);
$records = mysqli_fetch_assoc($sel);
$totalRecordwithFilter = $records['allcount'];



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
 as  c2 where c1.type='".$pagetype ."'  and c1.hospital_id = c2.id ".$searchQuery." order by ".$columnName." ".$columnSortOrder." limit ".$row.",".$rowperpage;
$empRecords = mysqli_query($cn, $empQuery);
$data = array();


while ($row = mysqli_fetch_assoc($empRecords)) {
   $data[] = array( 
      "name"=>$row['name'],
      "vacant"=>$row['vacant'],
      "capacity"=>$row['capacity'],
      "occupied"=>$row['occupied'],
      "type"=>$row['type'],
      "phone"=>$row['phone'],
      "updated_on"=>$row['updated_on'],
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