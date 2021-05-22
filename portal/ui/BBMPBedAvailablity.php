<?php
include('php/dbhandler.php');
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



      <div class="card-body">
        <h4 class="cs-card-title cs-card-title-bed-available">Bed Availability Status
          <!-- <span class="cs-card-sub-title"><span class="jk-font-color-grey">last updated :</span> 12/04/2021
            12:50
            PM
            <span class="jk-refresh-icon"><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" fill="#000000">
                <path d="M0 0h24v24H0V0z" fill="none" />
                <path d="M17.65 6.35C16.2 4.9 14.21 4 12 4c-4.42 0-7.99 3.58-7.99 8s3.57 8 7.99 8c3.73 0 6.84-2.55 7.73-6h-2.08c-.82 2.33-3.04 4-5.65 4-3.31 0-6-2.69-6-6s2.69-6 6-6c1.66 0 3.14.69 4.22 1.78L13 11h7V4l-2.35 2.35z" />
              </svg></span>
            <span class="jk-refresh-text cs-primary">Refresh</span></span> -->
        </h4>

        <?php
        if (count($data) > 0) { ?>

          <?php for ($i = 0; $i < count($data); $i++) {
            if ($i % 2 == 0) {   ?>
              <div class="jk-bed-card-holder">
              <?php } ?>
              <!-- card 1 search contents -->
              <div class="cs-white-cards jk-halfcard">
                <div class="row jk-bed-available-row" id="cs-doctor-consultations-card">
                  <div class="col-md-12 jk-text-align-center">
                    <h5 class="jk-font-bold"><?php echo $data[$i]['bed_bedtype']; ?> Bed</h5>
                  </div>
                </div>
                <div class="row jk-bed-available-row" id="cs-doctor-consultations-card">
                  <div class="col-md-4 jk-bed-count-cards jk-bed-available jk-bed-count-total" data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>">
                    <h3 data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>" class="jk-font-bold jk-bed-count-text-total "><?php echo $data[$i]['bed_capacity']; ?></h3>
                    <p data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>">
                      <large>
                        <span data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>" style="display:inline-block;" class="jk-font-bold">Total</span><br />


                      </large>
                    </p>
                  </div>
                  <div class="col-md-4 jk-bed-count-cards jk-bed-available jk-bed-count-occupied" id="cs-doctor-consultation" data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>">
                    <h3 data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>" class="jk-font-bold jk-bed-count-text-occupied "><?php echo $data[$i]['bed_occupied']+$data[$i]['bed_blocked']; ?></h3>
                    <p data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>">
                      <large>
                        <span data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>" style="display:inline-block;" class="jk-font-bold">Occupied</span>
                        <!-- <span data-bed_type="<?php //echo $data[$i]['bed_bedtype']; ?>" style="display:inline-block;" class="jk-font">
                          <?php //$yesterday_occupied = bedprogress($data[$i]['bed_bedtype'], 'occupied');
                          // $today_occupied = $data[$i]['bed_occupied'];
                          // if ($today_occupied >= $yesterday_occupied) {
                          //   $increase = $today_occupied - $yesterday_occupied;
                          //   echo $increase . " Occupied";
                          // }
                          ?>
                        </span> -->

                      </large>
                    </p>
                  </div>
                  <div class="col-md-4 jk-bed-count-cards jk-bed-available jk-bed-count-vacant" id="cs-doctor-consultation" data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>">
                    <h3 data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>" class="jk-font-bold jk-bed-count-text-vacant "><?php echo $data[$i]['bed_vacant']; ?></h3>
                    <p data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>">
                      <large>
                        <span data-bed_type="<?php echo $data[$i]['bed_bedtype']; ?>" style="display:inline-block;" class="jk-font-bold">Vacant</span>
                        <!-- <span data-bed_type="<?php //echo $data[$i]['bed_bedtype']; ?>" style="display:inline-block;" class="jk-font">
                        <?php //$beddd = bedprogress($data[$i]['bed_bedtype'], 'vacant'); echo $beddd;  ?>
                          <?php //$yesterday_vacant = bedprogress($data[$i]['bed_bedtype'], 'vacant');
                          /*$today_vacant = $data[$i]['bed_vacant'];
                          if ($today_vacant >= $yesterday_vacant) {
                            $increase = $today_vacant - $yesterday_vacant;
                            echo $increase . " Vacant Today";
                          }*/
                          ?>
                        </span> -->

                      </large>
                    </p>
                  </div>
                </div>
                <div class="row jk-bed-available-row" id="cs-doctor-consultations-card">
                  <div class="col-md-12 jk-text-align-right ">
                    <!-- <h6 class="jk-bed-current-status-success"> +10 From Yesterday</h4> -->
                  </div>
                </div>
              </div>
              <?php if ($i % 2 != 0) {   ?>
              </div>
            <?php } ?>
            <!-- card 1 search contents -->

          <?php } ?>

        <?php } ?>

        <!-- card 1 search contents -->

        <button onclick="topFunction()" id="myBtn" title="Go to top" style="display: none;">Top</button>
      </div>

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