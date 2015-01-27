function initialize() {
	var map_canvas = document.getElementById('map_canvas');
	var map_options = {
			center: new google.maps.LatLng(18.3, -66.3),
		zoom: 9,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	var map = new google.maps.Map( map_canvas, map_options );			
}

google.maps.event.addDomListener( window, 'load', initialize );

angular.module( "dqmc", [] )
	.controller( "DqmcMain", [ '$scope', function( $scope ) {
		$scope.testText = "Some Text";
	}])
   .directive( "dqmcMap", function() {
      return {
         scope: {},
         link: function( scope, element, attrs ) {
           console.log( "I'm in an AngularJS Directive!" ); 
         }
      };
   });
