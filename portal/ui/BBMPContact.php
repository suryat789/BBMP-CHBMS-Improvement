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
            <a class="nav-link  jk-bed-availability" aria-current="true" href="#">Bed Availability</a>
          </li>
          <li class="nav-item">
            <a class="nav-link jk-bed-faq " href="#">FAQ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link jk-bed-contact active" href="#">Contact</a>
          </li>
        </ul>
      </div>
      <!-- router contents -->

      <div class="card-body">
        <h4 class="cs-card-title cs-card-title-bed-available">Contact Us
        </h4>
        <div class="cs-white-cards" style="margin-top: 2%;">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Sl No.</th>
                <th scope="col">Zone</th>
                <th scope="col">Minister Incharge</th>
                <th scope="col">Zonal Commissioner</th>
                <th scope="col">Zonal Nodal Officer</th>
                <th scope="col">Zonal Joint Commissioner</th>
                <th scope="col">Health Officer</th>
                <th scope="col">Helpline</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">1</th>
                <td> West</td>
                <td> Dr C N Ashwathnarayan </td>
                <td> Shri Basavaraj S, IAS </td>
                <td> Shri Ujjwal Ghosh, IAS</td>
                <td> Shri Shivaswamy N</td>
                <td> Dr Manoranjan Hegde</td>
                <td> <a href="tel:080-68248454"> 080-68248454</a> </td>
              </tr>
              <tr>
                <th scope="row">2</th>
                <td> East</td>
                <td> Shri V Somanna </td>
                <td> Shri Manoj Jain ,IAS</td>
                <td> Shri Manoj Kumar Meena,IAS</td>
                <td> Smt Pallavi K R</td>
                <td> Dr Siddappaji </td>
                <td> <a href="tel:080-25355100">080-25355100</a> </td>
              </tr>
              <tr>
                <th scope="row">3</th>
                <td> Yelahanka </td>
                <td> Shri S R Vishwanath </td>
                <td> Shri Randeep D, IAS </td>
                <td> Shri Anbhukumar, IAS </td>
                <td> Dr Ashok </td>
                <td> Dr Bhagyalakshmi</td>
                <td> <a href="tel:080-61972844">080-61972844</a> </td>
              </tr>
              <tr>
                <th scope="row">4</th>
                <td> South </td>
                <td> Shri R Ashok</td>
                <td> Smt Thulasi Maddineni,IAS</td>
                <td> Shri Pankaj Kumar Pandey, IAS</td>
                <td> Shri Veerabhadraswamy </td>
                <td> Dr Shivakumar</td>
                <td> <a href="tel:080-35254759"> 080-35254759</a> </td>
              </tr>
              <tr>
                <th scope="row">5</th>
                <td> Dasarahalli </td>
                <td> Shri K Gopalayya </td>
                <td> Shri Raveendra S G, ISS</td>
                <td> Dr P C Jaffer, IAS</td>
                <td> Shri K Narasimhamurthy</td>
                <td> Dr Lokesh </td>
                <td> <a href="tel:080-29590057"> 080-29590057</a> </td>
              </tr>
              <tr>
                <th scope="row">6</th>
                <td> R R Nagar </td>
                <td> Shri S T Someshekar</td>
                <td> Shri B Reddy Shankar Babu,IAS </td>
                <td> Dr R Vishal, IAS</td>
                <td> Shri Nagaraj </td>
                <td> Dr Balasundar </td>
                <td> <a href="tel:080-61972848">080-61972848</a>
              </tr>
              <tr>
                <th scope="row">7</th>
                <td> Bommanahalli</td>
                <td> Shri S Sureshkumar</td>
                <td> Shri Rajendra Cholan, IAS</td>
                <td> Shri Ravikumar Surapur, IAS</td>
                <td> Shri M Ramakrishna </td>
                <td> Dr Savitha </td>
                <td> <a href="tel:080-68175614">080-68175614</a> </td>
              </tr>
              <tr>
                <th scope="row">8</th>
                <td> Mahadevapura</td>
                <td> Shri B A Basaraju </td>
                <td> Shri Randeep D, IAS</td>
                <td> Dr N Manjula, IAS</td>
                <td> Shri R Venkarachalapathy</td>
                <td> Dr Surendra </td>
                <td> <a href="tel:080-61972860">080-61972860</a></td>
              </tr>
            </tbody>
          </table>
        </div>

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