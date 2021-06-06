<!doctype html>
<html lang="en">
<?php include('php/connection.php'); ?>
<?php
  # Start Caching for 120 seconds
    Header("Cache-Control: must-revalidate");    
    $ExpStr = "Expires: " . date("d/m/Y h:i:s a", strtotime("+120 seconds"));
    Header($ExpStr);
    #end
?>
<?php include('php/queue.php'); ?>
<?php
$pagetype = $_GET['type'];


if ($pagetype == "") {
  $pagetype = "General";
}

?>

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
	<!-- Commit Test -->
      <!-- router contents -->
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs text-center">
          <li class="nav-item ">
            <a class="nav-link active jk-home-link" aria-current="true" href="#">Queue Status</a>
          </li>
          <!--<li class="nav-item">
            <a class="nav-link jk-bed-availability" href="#">Bed Availability</a>
          </li>-->
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
        <h4 class="cs-card-title">Bed Allocation Queue - <?php echo $pagetype; ?>
          <span class="cs-card-sub-title"><span class="jk-font-color-grey">last updated :</span> <?php echo date("d/m/Y h:i A", $_SERVER['REQUEST_TIME']); ?>

        </h4>
        <!-- card 2 patient waiting for doctors contents -->
        <div class="cs-white-cards" style="margin-top: 2%;">
          <div class="container-fluid">
            <div class="table-responsive">

                  <table id='empTable' class="table table-striped table-bordered nowrap" style="width:100%">
                    <thead>
                      <tr>
                        <th scope="col">Queue No.</th>
                        <th scope="col">BU Number</th>
                        <th scope="col">SRF Number.</th>
                        <th scope="col">Added on</th>
                      </tr>
                    </thead>
                  </table>
                </div>
              </div>
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
    $('#empTable').DataTable({
      'processing': true,
      'serverSide': true,
      'serverMethod': 'post',
      'ajax': {
        'url': 'php/zonejaxfile.php?bedpage=<?php echo $pagetype; ?>'
      },
      'columns': [{
          data: 'patient_id'
        },
        {
          data: 'bucode'
        },
        {
          data: 'srf_number'
        },
        {
          data: 'time_added_to_queue'
        },

      ]
    });
  });
</script>