<!doctype html>
<html lang="en">

<head>
    <?php
    include('php/connection.php');
    include('php/query.php');
    include('php/pat.php');
    include('php/patientwb.php');
    ?>
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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
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
                        <a class="nav-link  jk-home-link" aria-current="true" href="index.php">Queue Status</a>
                    </li>
                    <!--<li class="nav-item">
                        <a class="nav-link jk-bed-availability" href="#">Bed Availability</a>
                    </li>-->
                    <li class="nav-item">
                        <a class="nav-link jk-bed-faq active" href="#">FAQ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link jk-bed-contact" href="#">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- router contents -->

            <div class="card-body">
                <h4 class="cs-card-title">Frequently Asked Questions </h4>


                <!-- card 2 patient waiting for doctors contents -->
                <div class="cs-white-cards" style="margin-top: 2%;">

                    <ol>
                        <li>
                            <h6 class="jk-font-bold">How do I access and use the dashboard?</h6>
                            <p class="jk-s-no-ans"> Please use the below URL from your laptop or mobile device to use
                                the dashboard -</p>
                        </li>

                        <li>
                            <h6 class="jk-font-bold">What is my BU no.?</h6>
                            <p class="jk-s-no-ans">When you call ApthaMitra call centre number with your SRF no. as
                                mentioned on your RT-PCR test report, you will be sent an SMS with your BU no. This BU
                                no. will serve as your unique patient identifier no. Using this no. you will be able to
                                search your status on the dashboard. </p>
                            <p class="jk-s-no-ans">You can also be allotted a temporary BU no. represented by your TBU
                                no. sent to you in an SMS. You can get a TBU no. if you have a doctor’s diagnosis of
                                COVID based on a non-RT-PCR test, such as CT-scan or X-Ray reports.</p>
                        </li>

                        <li>
                            <h6 class="jk-font-bold">What is my SRF no.?</h6>
                            <p class="jk-s-no-ans"> You can locate your SRF no. on the RT-PCR test report. </p>
                        </li>

                        <li>
                            <h6 class="jk-font-bold">What is my Zone no.? </h6>
                            <p class="jk-s-no-ans"> Based on your physical location, you are identified as a resident of
                                a specific zone no. in Bangalore. After you have spoken with ApthaMitra call centre, the
                                sms they send will carry your zone no. </p>
                        </li>

                        <li>
                            <h6 class="jk-font-bold">Where do I start to get in the queue?</h6>
                            <p class="jk-s-no-ans">Below are the steps you need to follow to be on the queue -
                            <ol class="jk-order-list">
                                <li> Call the Bangalore COVID helpline desk at Apthamitra for a preliminary
                                    consultation on
                                    the next steps.
                                    <ol class="jk-order-list2">
                                        <li> If you have already been diagnosed with Covid and have your RT-PCR test
                                            report handy,
                                            please locate the 13-digit SRF no. on the report and call ApthaMitra to
                                            find out whether
                                            you need hospitalization. </li>
                                        <li> If you are advised to self-isolate at home, please follow all Covid
                                            isolation protocols
                                            at home. </li>
                                        <li> If you are advised to get hospitalized, you will be sent an SMS with
                                            your zone details
                                            based on your physical location and a BU no. allocated to you. The BU
                                            no. will be BU
                                            followed by 6 digits - BU123456.
                                        </li>
                                    </ol>
                                </li>
                                <li> Once you get your BU no., you can search your status on the dashboard landing
                                    page by
                                    entering either your BU no. or SRF no. and the last 4 digits of your registered
                                    phone
                                    number. Below is a screenshot of where you can locate the fields
                                </li>
                            </ol>
                            </p>
                        </li>

                        <li>
                            <h6 class="jk-font-bold">My status shows “In queue for doctor’s consultation (1st Triage)”
                                or “In triaging queue”. What does this mean? </h6>
                            <p class="jk-s-no-ans"> This means you have been added to the queue for your Doctor’s
                                consultation. Based on this consultation, you will be allocated to queues of one of the
                                below 5 kinds of beds -
                            <ol class="jk-order-list">
                                <li> General</li>
                                <li> HDU (High Dependency Units) Beds</li>
                                <li> ICU (Intensive Care Unit) Beds</li>
                                <li> ICU Ventilator (Intensive Case Unit with Ventilators) Beds</li>
                                <li> ER Queue</li>
                            </ol>
                            </p>
                        </li>

                        <li>
                            <h6 class="jk-font-bold">My condition is deteriorating while waiting in the triaging queue.
                                How do I get immediate hospitalization or medical assistance?
                            </h6>
                            <p class="jk-s-no-ans"> Bring it to a doctor’s attention immediately, if you can. Meanwhile,
                                please call up 1912 to talk to a zonal doctor and get priority access to ER queue
                                (emergency Response Queue)
                            </p>
                        </li>

                        <li>
                            <h6 class="jk-font-bold">I was able to see my status on the dashboard, but I don’t find my
                                BU no. on the dashboard anymore. Why?
                            </h6>
                            <p class="jk-s-no-ans"> A patient will be tracked only till he or she gets allocated a bed.
                                If you are not able to see your status on the dashboard, either of the 2 cases are
                                possible -

                            <ol class="jk-order-list">
                                <li> You don’t have a BU no. - You need to get a new BU no. Please refer to
                                    questions Q#5
                                    above.</li>
                                <li> You have been allocated a bed - If you were able to check your status until a
                                    few
                                    minutes ago and can no more find your information on the queueing dashboard, you
                                    may
                                    have already received a bed allocation. Please check your SMS.</li>
                                <li> You have dropped out of the queue - You may have recovered and have indicated
                                    that you
                                    no longer need hospitalization.</li>
                            </ol>





                            </p>
                        </li>
                    </ol>


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