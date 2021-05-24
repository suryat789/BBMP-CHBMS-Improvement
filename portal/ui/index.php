<!doctype html>
<html lang="en">
<?php
  include('php/connection.php');
  include('php/query.php');
  include('php/pat.php');
  include('php/patientwb.php');
  $curDate = date('Y-m-d H:i:s');
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
  <link rel="stylesheet" href="css/styles.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700 & display=swap" />
  
  <script src="js/header.js" type="text/javascript" defer></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            <a class="nav-link active jk-home-link" aria-current="true" href="#">Queue Status</a>
          </li>
          <li class="nav-item">
            <a class="nav-link jk-bed-availability" href="#">Bed Availability</a>
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
        <!-- <h4 class="cs-card-title">Patient Queue Status <span class="cs-card-sub-title"><span class="jk-font-color-grey">last updated :</span> <?php //echo date("d/m/Y h:i A", $_SERVER['REQUEST_TIME']);?> -->
                  <!--<span class="jk-refresh-icon">
              <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" fill="#000000">
                <path d="M0 0h24v24H0V0z" fill="none" />
                <path d="M17.65 6.35C16.2 4.9 14.21 4 12 4c-4.42 0-7.99 3.58-7.99 8s3.57 8 7.99 8c3.73 0 6.84-2.55 7.73-6h-2.08c-.82 2.33-3.04 4-5.65 4-3.31 0-6-2.69-6-6s2.69-6 6-6c1.66 0 3.14.69 4.22 1.78L13 11h7V4l-2.35 2.35z" />
              </svg>
            </span>
         <span class="jk-refresh-text cs-primary">Refresh</span>-->
        </h4>
        <!--<div class="alert alert-primary jk-alert-danger" role="alert">
          <span class="jk-info-icon"> <svg class="MuiSvgIcon-root-603" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
              <path fill="none" d="M0 0h24v24H0z"></path>
              <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z">
              </path>
            </svg></span>
          This is only for patients who require hospitalisation. If you are not already on the queue and require
          hospitalisation then call 1912.
        </div>-->
        <!-- card 1 search contents -->
        <div class="cs-white-cards">
          <div class="card-body">
            <h6 class="card-title cs-card1-header  jk-font-bold">Check Queue Position </h6>
            <h6 class="card-subtitle cs-card1-helper-text">Search by BU Number(e.g. BU12355) along with the last 4 digit of the registered mobile number.</h6>
            
            <form name="myForm" class="row g-3 needs-validation cs-card1-form jk-dashboard-form" novalidate>
              <div class="col-md-4 jk-no-padding-left">
                <!-- <label for="validationCustom01" class="form-label">First name</label> -->
                <input type="text" name="buNumber"  class="form-control" id="validationCustom01" placeholder="BU/TBU Number (e.g. BU123456)" required>
                
              </div>
              <div class="col-md-4 jk-no-padding-left">
                <!-- <label for="validationCustom02" class="form-label">Last name</label> -->
                <!-- <span class="input-group-text" id="inputGroupPrepend">@</span> -->
                <input type="text" name="mobNumber" class="form-control" id="validationCustom03" placeholder="Last 4 digit of registered Mobile Number" required>
                
              </div>

              <div class="col-md-3 jk-no-padding-left">
                <button class="btn cs-primary-button" type="submit">SEARCH</button>
              </div>
            </form>
            <div class="jk-margin-top-s jk-alert-danger-text jk-search-helper-text" id="jk-search-helper-text">
              <div>
                Note:
              </div>
              <div>
                • This is only for patients who require hospitalization.
              </div>
              <div>
                • Enter your BU Number and the last 4 digits of your registered phone number to look up your
                position in the queue.
              </div>
              <div>
                • If you are not on the queue yet and need medical assistance, please call 1912.
              </div>              
            </div>
            <!-- search result  -->
            <div class="col-md-3 col-sm-6 cs-grey-sub-cards jk-search-result" id="jk-search-result-element">
                <div class="jk-search-response-holder">
                  <div class="qType">                  
                  </div>
                </div>
                <div class="jk-search-response-holder">
                  <div class="jk-search-response-label lbqPosition"></div>
                  <div class="jk-search-response-value jk-font-bold qPosition"></div>
                </div>
                <div class="jk-search-response-holder">
                  <div class="jk-search-response-label lbqZone"></div>
                  <div class="jk-search-response-value qZone"></div>
                </div>
                <div class="jk-search-response-holder">
                  <div class="jk-search-response-value msg"></div>
                </div>
                <div class="jk-search-response-holder">
                  <div class="jk-search-response-value" id="notfound">You are currently not in any Queue. If you need medical assistance, call 1912.
                  </div>                
                </div>
            </div>

            <div class="col-md-3 col-sm-6 cs-grey-sub-cards jk-search-result" id="jk-search-result-error">
                <div class="jk-search-response-holder">
                  <div class="jk-search-response-value">
                  Please enter minimum 4 digit of BU/TBU No. and last 1 digit of mobile no.
                  </div>                
                </div>
            </div>
            <!-- search result  -->
          </div>
        </div>
        <!--card 1 search contents-->        
        <?php foreach ($data as $key=>$dataVal) {
          $cate= $key;
          ?>
        
        <!-- card 2 patient waiting for doctors contents -->
        <div class="cs-white-cards" style="margin-top: 2%;">
          <h6 class="card-title  jk-font-bold">No. of Patients Waiting in the <?php echo $key;?> Queue</h6>
          <div class="row" id="cs-doctor-consultations-card">
            <?php             
            foreach ($dataVal as $key1=>$dataVal1) { ?>
              
              <div class="col-md-3 col-sm-6 cs-grey-sub-cards jk-zone-card" data-zone_type="<?php echo $dataVal1['queue_name']?>">
              <input type="hidden" id="category<?php echo $dataVal1['queue_name']?>" value="<?php echo $cate;?>">
                <h6 data-zone_type="<?php echo $dataVal1['queue_name']; ?>"><?php echo $dataVal1['queue_name']; ?></h6>
                <h3 data-zone_type="<?php echo $dataVal1['queue_name']; ?>" class="jk-font-bold"><?php echo $dataVal1['total']; ?></h3>
                <p>
                  <small>
                  <span data-zone_type="<?php echo $dataVal1['queue_name']; ?>" class="jk-font-color-grey" style="display:inline-block;">Next in Line</span><br/>
                    <span data-zone_type="<?php echo $dataVal1['queue_name']; ?>" style="display:inline-block;"><?php echo $dataVal1['bucode']; ?>
                    <span data-zone_type="<?php echo $dataVal1['queue_name']; ?>" class="jk-timeperiod-element">
                    <!-- (
                      <?php                      
                        /*$currentdate =  strtotime(date('Y-m-d H:i:s'));
                        $createdDate =   strtotime($dataVal1['time_added_to_queue']);
                        $difference  =  $currentdate - $createdDate;
                        $minutes =  floor($difference/(60));
                        $hours = floor($difference/(60*60));
                        $days = floor($difference/(60*60*24));

                        if($minutes <60){
                            echo $minutes .' Mnts';
                        }elseif($hours <=24){
                        echo $hours .' Hr';    
                        }else{
                            echo $days . ' Days';
                        }*/
                        ?> Ago) -->
                      </span>
                    </span>
                  </small>
                </p>
              </div>
            <?php } ?>
          </div>
        </div>
        <?php }?>
        <br/>                
        <!-- card 2 patient waiting for doctors contents -->
        <!-- card 3 patient waiting contents -->        
        <div class="alert alert-primary jk-alert-danger" role="alert">
          <span class="jk-info-icon"> <svg class="MuiSvgIcon-root-603" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
              <path fill="none" d="M0 0h24v24H0z"></path>
              <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z">
              </path>
            </svg></span>
          <!--If you are in the queue and situation deteriorating, call 1912. Doctors will assess if you need to urgent
          attention and move you to the critical queue. This is only for patients who require hospitalisation.-->
          If you are in the triaging queue and your condition is deteriorating, call 1912 immediately. Doctors will assess if you need urgent attention and move you to the ER Queue (Emergency Response queue).
        </div>
        <!-- card 3 patient waiting contents -->
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