<?php
include('php/connection.php');
$pagetype = $_GET['type'];


if ($pagetype == "") {
  $pagetype = "General";
}

?>
<?php
  # Start Caching for 120 seconds
    Header("Cache-Control: must-revalidate");    
    $ExpStr = "Expires: " . date("d/m/Y h:i:s a", strtotime("+120 seconds"));
    Header($ExpStr);
    #end
?>
<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap CSS -->
  <link rel="icon" href="assests/logo.png" type="image/x-icon">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
  <title>BBMP CHBMS Dashboard</title>
  <meta name="description" content="Dashboard to show details of Bed Allocation">
  <meta name="author" content="Jagan">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
  <link rel="stylesheet" href="css/styles.css">
  <script src="js/header.js" type="text/javascript" defer></script>
  <!-- Datatable CSS -->
  <link href='//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css' rel='stylesheet' type='text/css'>

  <!-- jQuery Library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <!-- Datatable JS -->
  <script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>



</head>

<body>
  <!-- nav bar contents -->
  <!-- <header-component></header-component> -->
  <!-- nav bar contents -->


  <div class="cs-card-body">


    <div class="card ">
      <!-- router contents -->
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs text-center">
          <li class="nav-item ">
            <a class="nav-link  jk-home-link" href="#">Queue Status</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active jk-bed-availability" aria-current="true" href="#">Bed Availability</a>
          </li>
          <li class="nav-item">
            <a class="nav-link jk-bed-faq " href="#">FAQ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link jk-bed-contact" href="#">Contact</a>
          </li>
        </ul>
      </div>
      <!-- router contents -->

      <button class="btn-group jk-btn-back jk-home-link" role="group">
        <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M16 7H3.83L9.42 1.41L8 0L0 8L8 16L9.41 14.59L3.83 9H16V7Z" fill="#0000FF" />
        </svg>Back
      </button>
      <div class="card-body">
        <h4 class="cs-card-title">Bed Availability - <?php echo $pagetype; ?> Bed
          <!--  <span class="cs-card-sub-title"><span class="jk-font-color-grey">last updated :</span> 12/04/2021 12:50
              PM
              <span class="jk-refresh-icon"><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24"
                  width="24px" fill="#000000">
                  <path d="M0 0h24v24H0V0z" fill="none" />
                  <path
                    d="M17.65 6.35C16.2 4.9 14.21 4 12 4c-4.42 0-7.99 3.58-7.99 8s3.57 8 7.99 8c3.73 0 6.84-2.55 7.73-6h-2.08c-.82 2.33-3.04 4-5.65 4-3.31 0-6-2.69-6-6s2.69-6 6-6c1.66 0 3.14.69 4.22 1.78L13 11h7V4l-2.35 2.35z" />
                </svg></span>
              <span class="jk-refresh-text cs-primary">Refresh</span></span> -->
        </h4>

        <!-- <div class="alert alert-primary jk-alert-info" role="alert">
              <span class="jk-info-icon"> <svg class="MuiSvgIcon-root-603" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
                  <path fill="none" d="M0 0h24v24H0z"></path>
                  <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z">
                  </path>
                </svg></span>
              Please call 1912 to get on the queue.
            </div> -->

        <!-- card 2 patient waiting for doctors contents -->
        <div class="cs-white-cards jk-bed-table-padding" style="margin-top: 2%;">
          <form class="row g-3 needs-validation cs-card1-form jk-bed-table-form " novalidate style="margin-bottom: 10px">
            <div class="col-md-3 jk-no-margin jk-mobile-display-none">

            </div>
            <div class="col-md-3 jk-no-margin jk-mobile-display-none">

            </div>
            <!--               <div class="col-md-3 jk-no-margin">
                <label for="validationCustom02" class="form-label jk-no-margin">Hospital Type</label>
                
                <select class="form-select" id="validationCustom04" required>
                  <option selected>All</option>
                  <option value="1">Government hospital</option>
                  <option value="2">Private hospital</option>
                </select>
                <div class="valid-feedback">
                  Looks good!
                </div>
              </div>
              <div class="col-md-3 jk-no-margin">
                <label for="validationCustom02" class="form-label jk-no-margin">Hospital</label>
                
                <input type="text" class="form-control" id="validationCustom03" placeholder="Search" required>
                <div class="valid-feedback">
                  Looks good!
                </div>
              </div>
 -->

          </form>
          <!-- Table -->
          <div class="container-fluid">
            <div class="table-responsive">

              <table id='empTable' class="table table-striped table-bordered nowrap" style="width:100%">
                <thead>
                  <tr>
                    <th>Hospital Name</th>
                    <th>Zone</th>
                    <th>Available</th>
                    <th>Total</th>
                    <th>Occupied</th>
                    <th>Hospital Type</th>
                    <th>Contact</th>
                    <th>Last Updated
                    </th>
                  </tr>
                </thead>

              </table>
            </div>
          </div>

        </div>
        <!-- card 2 patient waiting for doctors contents -->
        <button onclick="topFunction()" id="myBtn" title="Go to top" style="display: none;">Top</button>
      </div>
    </div>
  </div>
  <!-- Scripts contents -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous">
  </script>
  <script src="js/scripts.js"></script>
  <!-- Scripts contents -->
</body>

</html>
<script type="text/javascript">
  $(document).ready(function() {
    var table = $('#empTable').DataTable({
      responsive: true,
      'processing': true,
      'serverSide': true,
      'serverMethod': 'post',
      'ajax': {
        'url': 'php/bedselectedajaxfile.php?bedtypepage=<?php echo $pagetype; ?>'
      },
      'columns': [{
          data: 'name'
        },
        {
          data: 'zone'
        },
        {
          data: 'vacant'
        },
        {
          data: 'capacity'
        },
        {
          data: 'occupied'
        },
        {
          data: 'type'
        },
       
        { 
         data: "phone",
         "render": function(data, type, row, meta){
            if(type === 'display'){
                data = '<a href="tel:'+ data +'">' + data + '</a>'
            }
            return data;
          }
        },

        {
          data: 'updated_on'
        },
      ],
      'columnDefs': [ {
        'targets': [0, 1, 2, 3, 4, 5, 6, 7], // column index (start from 0)
        'orderable': false, // set orderable false for selected columns
     }]

    });

   // new $.fn.dataTable.FixedHeader(table);
  });
</script>