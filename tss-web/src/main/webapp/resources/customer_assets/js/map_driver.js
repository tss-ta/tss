var geocoder;
var markerFrom;
var fromCoord;
var toCoord;
var markerTo;
var map;
var directionsDisplay;
var directionsService;
function initialize() {
	if (markerFrom != null) {
		removeFromMarker();
		$("#fromc").val("");
	}
	fromCoord = null;
	if (markerTo != null) {
		removeToMarker();
		$("#toc").val("");
	}
	toCoord = null;
	geocoder = new google.maps.Geocoder();
	var mapOptions = {
		center : new google.maps.LatLng(50.5, 30.5),
		zoom : 8,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

	google.maps.event.addListener(map, 'click', function(event) {
		placeMarker(event.latLng);
	});

	directionsDisplay = new google.maps.DirectionsRenderer();
	directionsService = new google.maps.DirectionsService();
	directionsDisplay.setOptions({
		suppressMarkers : true,
		suppressInfoWindows : true
	});

}
function geolocationSuccess(position) {
	if (markerFrom != null)
		removeFromMarker();
	var location = new google.maps.LatLng(position.coords.latitude,
			position.coords.longitude);
	codeLatLngFrom(location);
	placeFromMarker(location);
	map.setCenter(location);

	if (fromCoord != null && toCoord != null) {
		createRoute(fromCoord, toCoord);
	}
}
function geolocationFailure(positionError) {
	alert("Geolocation Error");
}
function placeFromMarker(location) {
	$("#fromc").val(location);
	fromCoord = location;
         var pinImage = new google.maps.MarkerImage("/resources/customer_assets/img/from.png",
            new google.maps.Size(32, 32),
            new google.maps.Point(0, 0),
            new google.maps.Point(16, 32));
	markerFrom = new google.maps.Marker({
		position : location,
		map : map,
		icon: pinImage,
	});
	$("#fromAddrMessage").text("");
	google.maps.event.addListener(markerFrom, 'click', function(event) {
		removeFromMarker();
		$("#fromc").val("");
		$("#fromAddr").val("");
	});
}


function placeToMarker(location) {
	$("#toc").val(location);
	toCoord = location;
         var pinImage = new google.maps.MarkerImage("/resources/customer_assets/img/to.png",
            new google.maps.Size(32, 32),
            new google.maps.Point(0, 0),
            new google.maps.Point(16, 32));
	markerTo = new google.maps.Marker({
		position : location,
		map : map,
		icon: pinImage,
	});
	$("#toAddrMessage").text("");
	google.maps.event.addListener(markerTo, 'click', function(event) {
		removeToMarker();
		$("#toc").val("");
		$("#toAddr").val("");
	});
}
function placeMarker(location) {
	if (fromCoord != null && toCoord != null) {
		createRoute(fromCoord, toCoord);
	}
}

function codeLatLngFrom(location) {
	geocoder.geocode({
		'latLng' : location
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[0]) {
				$("#fromAddr").val(results[0].formatted_address);
			}
		} else {
			alert("Geocoder failed due to: " + status);
		}
	});
}


function codeAddressFrom() {;
	var address = $("#fromAddr").val();
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			fromCoord = results[0].geometry.location;
			$("#fromc").val(fromCoord);
			placeFromMarker(fromCoord);
			$("#fromAddrMessage").text("");
		} else {
			$("#fromc").val("");
			fromCoord = null;
			$("#fromAddrMessage").text("Initial address is not found");
		}
	});
}

function codeAddressTo() {
	var address = $("#toAddr").val();
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			toCoord = results[0].geometry.location;
			$("#toc").val(toCoord);
			placeToMarker(toCoord);
			if (fromCoord != null && toCoord != null) {
				createRoute(fromCoord, toCoord);
				$("#toAddrMessage").text("");
			}
		} else {
			$("#toc").val("");
			toCoord = null;
			$("#toAddrMessage").text("Destination address is not found");

		}
	});

}

function createRoute(from, to) {
	directionsDisplay.setMap(map);
	var request = {
		origin : from,
		destination : to,
		travelMode : google.maps.TravelMode.DRIVING,
		unitSystem : google.maps.UnitSystem.METRIC,
		optimizeWaypoints : true,
		provideRouteAlternatives : true,
		avoidHighways : true,
		avoidTolls : true
	};
	directionsService.route(request, function(result, status) {
		if (status == google.maps.DirectionsStatus.OK) {
			directionsDisplay.setDirections(result);
			var routes = result.routes;
			var leg = routes[0].legs;
			var lenght = leg[0].distance.text;
			$("#route_distance").text(
					result.routes[0].legs[0].distance.value);
			var duration = leg[0].duration.text;
		}
	});
}


function showonmap() {
//	if (markerFrom != null) {
//		removeFromMarker();
//		$("#fromc").val("");
//	}
//	if (markerTo != null) {
//		removeToMarker();
//		$("#toc").val("");
//	}
	codeAddressFrom();
	codeAddressTo();
}
function geoloc() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(geolocationSuccess,
				geolocationFailure);

	} else {
		alert("Your browser does not support geolocation");
	}
}