 var geocoder;
    var markerFrom;
    var fromCoord;
    var toCoord;
    var markerTo;
    var map ;
    var directionsDisplay;
var directionsService;
    function initialize() {
         if(markerFrom!=null){
           removeFromMarker();
           $( "#fromc" ).val("");
       }
       fromCoord=null;
       if(markerTo!=null){
           removeToMarker();
           $( "#toc" ).val("");
       }
       toCoord=null;
        geocoder = new google.maps.Geocoder();
        var mapOptions = {
            center: new google.maps.LatLng(50.5,30.5),
            zoom: 8,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map_canvas"),
                mapOptions);
                
    
        google.maps.event.addListener(map, 'click', function(event) {
            placeMarker(event.latLng);
        });
   
directionsDisplay = new google.maps.DirectionsRenderer();
 directionsService = new google.maps.DirectionsService();
directionsDisplay.setOptions( { suppressMarkers: true, suppressInfoWindows: true } );
    
}
 function geolocationSuccess(position) {
        if(markerFrom!=null)   removeFromMarker();
	var location = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
      //  fromCoord=location;
	codeLatLngFrom(location);
        placeFromMarker(location);
	map.setCenter(location);
        
         if(fromCoord!=null&&toCoord!=null){
         createRoute(fromCoord,toCoord);
     }
}
function geolocationFailure(positionError) {
	 alert("Geolocation Error");
}
 function placeFromMarker(location){
          // codeLatLngFrom(location);
            $( "#fromc" ).val(location);
             fromCoord=location;
             markerFrom = new google.maps.Marker({
                position: location,
                map: map,
                title:'From '
            });
             $( "#fromAddrMessage" ).text("");
             google.maps.event.addListener(markerFrom, 'click',function(event) {
                 removeFromMarker();
        //  markerFrom.setMap(null);
        //  markerFrom=null;
       //   fromCoord=null;
          $( "#fromc" ).val("");
          $("#fromAddr").val("");
        //   removeRoute();
        });
 }
function removeFromMarker(){
      markerFrom.setMap(null);
          markerFrom=null;
          fromCoord=null;
           removeRoute();
}

function removeToMarker(){
      markerTo.setMap(null);
          markerTo=null;
          toCoord=null;
           removeRoute();
}

function placeToMarker(location){
            // codeLatLngTo(location);
             $( "#toc" ).val(location);
             toCoord=location;
             markerTo = new google.maps.Marker({
                position: location,
                map: map,
                title:'To '
            }); 
             $( "#toAddrMessage" ).text("");
             google.maps.event.addListener(markerTo, 'click',function(event) {
                 removeToMarker();
        //  markerTo.setMap(null);
        //  markerTo=null;
        //  toCoord=null;
          $( "#toc" ).val("");
           $("#toAddr").val("");
        //   removeRoute();
        }); 
    }
        function placeMarker(location) {
            if(markerFrom==null){
              // fromCoord=location;
               codeLatLngFrom(location);
               placeFromMarker(location);
            }else{
                 if(markerTo==null){
           //toCoord=location;
           codeLatLngTo(location);
           placeToMarker(location);
            }
            }
            if(fromCoord!=null&&toCoord!=null){
                createRoute(fromCoord,toCoord);
              }
        //    codeLatLng(location);
        }
        
    function codeLatLngFrom(location) {
        geocoder.geocode({'latLng': location}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                 $("#fromAddr").val(results[0].formatted_address);
                }
            } else {
                alert("Geocoder failed due to: " + status);
            }
        });
    }
    
     function codeLatLngTo(location) {
        geocoder.geocode({'latLng': location}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $("#toAddr").val(results[0].formatted_address);
                }
            } else {
                alert("Geocoder failed due to: " + status);
            }
        });
    }
 function codeAddressFrom() {
  var address =  $("#fromAddr").val();
  //var address = componeFromAddr();
  // var address =  $("#fromstreet").val()+" , "+$("#fromhouse").val()+$("#fromzip").val()+" , Kyiv, Ukraine";
  //  $("#fromAddr").val(address);
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status === google.maps.GeocoderStatus.OK) {
       fromCoord=results[0].geometry.location;
       $( "#fromc" ).val(fromCoord);
       placeFromMarker(fromCoord);
        $( "#fromAddrMessage" ).text("");
      } else {
          $( "#fromc" ).val("");
           fromCoord=null;
        $( "#fromAddrMessage" ).text("Initial address is not found");
       
       // alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }
  
  function codeAddressTo() {
  var address =  $("#toAddr").val();
//  var address =componeToAddr();
  // var address =  $("#tostreet").val()+" , "+$("#tohouse").val()+$("#tozip").val()+" , Kyiv, Ukraine";
  //  $("#toAddr").val(address);
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status === google.maps.GeocoderStatus.OK) {
       toCoord=results[0].geometry.location;
       $( "#toc" ).val(toCoord);
       placeToMarker(toCoord);
       if(fromCoord!=null&&toCoord!=null){
         createRoute(fromCoord,toCoord);
          $( "#toAddrMessage" ).text("");
     }
      } else {
           $( "#toc" ).val("");
           toCoord=null;
        $( "#toAddrMessage" ).text("Destination address is not found");
        
      }
    }); 
  
  }


function createRoute(from,to) {
    directionsDisplay.setMap(map);
      var request = {
 origin: from,
 destination: to,
 travelMode: google.maps.TravelMode.DRIVING,
 unitSystem: google.maps.UnitSystem.METRIC,
 optimizeWaypoints: true,
 provideRouteAlternatives: true,
 avoidHighways: true,
 avoidTolls: true
};
directionsService.route(request, function(result, status) {
 if (status == google.maps.DirectionsStatus.OK) {
  directionsDisplay.setDirections(result);
  var routes = result.routes;
  var leg = routes[0].legs;
  var lenght = leg[0].distance.text;
  var duration = leg[0].duration.text;
 }
});
    }
   


function removeRoute(){
   directionsDisplay.setMap(null);
}
   
function showonmap() {
       if(markerFrom!=null){
           removeFromMarker();
           $( "#fromc" ).val("");
       }
       if(markerTo!=null){
           removeToMarker();
           $( "#toc" ).val("");
       }
        codeAddressFrom();
        codeAddressTo(); 
    }
function geoloc() {
      if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(
		            geolocationSuccess, geolocationFailure);
                    
	}
	else {
                alert("Your browser does not support geolocation");
	}
    }