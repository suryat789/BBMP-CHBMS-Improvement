const array = ['jagan', 'bacac'];
const validateForm = () => {
  let valid = false;
  let form = document.forms["myForm"]
  if (form["mobNumber"].value && form["buNumber"].value) {
    if (form["mobNumber"].value.length == 4 && form["buNumber"].value.length > 3) {
      valid = true;
    }
  }

  return valid;

}
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        event.preventDefault()
        event.stopPropagation()
        if (!validateForm()) { //!form.checkValidity() 
          form.classList.add('was-validated')
          mySubmitFunction(false)
        } else {
          form.classList.remove('was-validated');
          mySubmitFunction(true);
        }

      }, false)
    })
})()

//   function myFunction() {
//     var itm = document.getElementById("cs-doctor-consultation");
//     var cln = itm.cloneNode(true);
//     document.getElementById("cs-doctor-consultation").appendChild(cln);
//   }
//   myFunction()

document.querySelectorAll('.jk-home-link').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  window.location.href = "index.php";
}));
document.querySelectorAll('.jk-zone-card').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  var zonetype = event.target.getAttribute("data-zone_type");
  window.location.href = 'BBMPDoctorsConsultationQueue.php?type=' + zonetype;
}));
document.querySelectorAll('.jk-bed-card').forEach(element => element.addEventListener('click', function (event) {
  // do something  
  console.log(event);
  var ptype = event.target.getAttribute("data-patb_type");
  window.location.href = 'BBMPBedAllocationQueue.php?type=' + ptype;

}));


document.querySelectorAll('.jk-refresh-icon').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  window.location.reload();
}));
document.querySelectorAll('.jk-refresh-text').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  window.location.reload();
}));

document.querySelectorAll('.jk-bed-availability').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  window.location.href = "BBMPBedAvailablity.php#";
}));
document.querySelectorAll('.jk-bed-faq').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  window.location.href = "FAQ.php#";
}));

document.querySelectorAll('.jk-bed-available').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  var bedtype = event.target.getAttribute("data-bed_type");
  window.location.href = 'BBMPBedAvailabilitySelectedBed.php?type=' + bedtype;
}));
document.querySelectorAll('.jk-bed-contact').forEach(element => element.addEventListener('click', function (event) {
  // do something
  console.log(event);
  window.location.href = "BBMPContact.php#";
}));


document.getElementById("cs-doctor-consultation")

function mySubmitFunction(isValid = false) {
  var x = document.getElementById("jk-search-result-element");  
  var y = document.getElementById("jk-search-helper-text");
  var z = document.getElementById("notfound");
  var buNo= document.getElementById("validationCustom01").value;
  var mobileNo= document.getElementById("validationCustom03").value;
  z.style.display ="none";
  //alert(buNo);
  if (isValid) {
    
    $.ajax({
      type: 'POST',
      url: "php/searchQueueStatus.php",                     
      data: {buCode:buNo, phone:mobileNo}, 
      cache: false,
      dataType: 'json',      
      success: function(response){ 
        var obj = JSON.parse(JSON.stringify(response));
        console.log(obj);    
       // var rep = JSON.parse(response);           
              console.log(obj);
             if(obj.id==null){   
                            
                z.style.display = "block";
                $('div.lbqType').text('');
                $('div.qType').text("");                
                $('div.lbqPosition').text('');
                $('div.qPosition').text('');                
                $('div.lbqZone').text('');
                $('div.qZone').text('');
                $('div.msg').text("");
            }else{
              if(obj.queue_type =='triage'){
                $('div.lbqType').text('Queue Type');
                $('div.qType').text("Patients Waiting For Zonal Doctors Consultation (1st Triage )");                
                $('div.lbqPosition').text('Queue Position');
                $('div.qPosition').text(obj.earliest);                
                $('div.lbqZone').text('Queue Zone');
                $('div.qZone').text(obj.zone);
                $('div.msg').text("The patient is yet to be consulted by Zonal doctor. Please wait for the doctor's call");
              }else if(obj.queue_type =='bed'){
                
                $('div.lbqType').text('Queue Type');
                $('div.qType').text("Patients Waiting For Beds");                
                $('div.lbqPosition').text('Queue Position');
                $('div.qPosition').text(obj.earliest);                
                $('div.lbqZone').text('Bed Type');
                $('div.qZone').text(obj.queue_name);
                $('div.msg').text("The patient consultation is done has been put in the queue for " + obj.queue_name+" Bed");
              } 
              
            }             
              //$('#degInstitute').val(response.institution_id);
                                                                      
      }
  });
    x.style.display = "block";
    y.style.display = "none";
    //z.style.display = "none";

  } else {
    
    x.style.display = "none";
    y.style.display = "block";
    //z.style.display = "block";
  }
}


mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () { scrollFunction() };

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "block";
  } else {
    mybutton.style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0; // For Safari
  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}