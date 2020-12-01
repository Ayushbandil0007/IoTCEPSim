
/*$(function () {
    $(".draggable-x").draggable({
        axis: "x",
        containment: $('#draggable-container')
    });
*//*    $(".regionStyle").droppable({
        drop: function (event, ui) {
            $(this)
            .addClass("ui-state-highlight")
            .find("p")
            .html("Item Dropped!");
        }
    });*//*
});*/

$( document ).ready(function() {
    $("#draggable-x").sortable();
});

function submit(){
   var idsInOrder = $("#draggable-x").sortable("toArray");
   console.log(idsInOrder);
   let dataMap = new Map();
   var card = [document.getElementById("i1Switch").checked];
   dataMap.set("card", card);
   var bio = [document.getElementById("i2Switch").checked];
   dataMap.set("bio", bio);
   var pin = [document.getElementById("i3Switch").checked];
   dataMap.set("pin", pin);
   var rotP = [document.getElementById("i4Switch").checked, document.getElementById("i4AngDis").value, document.getElementById("i4Dur").value];
   dataMap.set("rotP", rotP);
   var ultra = [document.getElementById("i5Switch").checked, document.getElementById("i5Dis").value, document.getElementById("i5Dur").value];
   var rotN = [document.getElementById("i6Switch").checked, document.getElementById("i6AngDis").value, document.getElementById("i6Dur").value];

   var myUrl = window.location.href + 'getDA';
   var data = { order: idsInOrder, card: card, bio: bio, pin: pin, rotP: rotP, ultra: ultra, rotN: rotN };
   console.log(data);

   $.ajax({
       url: myUrl,
       method: "POST",
       data: data,
       success: function(result) {
         console.log(result.name);
         //$('#testTopic').text(result.name);
         $('#centralModal').modal('show');
         $('#myModalBody').html(result.value);
         $('#myModalLabel').html(result.severity);
       }
   });
}

    function myFunction() {
      var myUrl = window.location.href + 'add';
      $.ajax({
        url: myUrl,
        success: function(result) {
          console.log(result.name);
          $('#testTopic').text(result.name);
        }
      });
    }

    function openCity(evt, cityName) {
      var tabcontent, tablinks, rowContent, row;
      rowContent = document.getElementsByClassName("rowcontent");
      row = document.getElementById("row" + cityName);
      tabcontent = document.getElementsByClassName("tabcontent");
      console.log(tabcontent.length);

      rowContent.forEach(function (_row){
        _row.classList.add("hidden");
      });

      tabcontent.forEach(function (content) {
        content.style.display = "none";
       });

      row.className = row.className.replace(" hidden", "");

      tablinks = document.getElementsByClassName("tablinks");
      for (var i=0; i<3; i++){
        tablinks[i].className = tablinks[i].className.replace(" active", "");
      }
//      tablinks[0].className = tablinks[0].className.replace(" active", "");
//      tablinks[1].className = tablinks[1].className.replace(" active", "");
//      tablinks[2].className = tablinks[2].className.replace(" active", "");

      document.getElementById(cityName).style.display = "block";
      evt.currentTarget.className += " active";
    }