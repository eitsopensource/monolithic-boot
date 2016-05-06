(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('HomeController', function( $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav ) {

    /*-------------------------------------------------------------------
     * 		 				 	ATTRIBUTES
     *-------------------------------------------------------------------*/
    //----STATES
		   

	//-----
	/**
	 * 
	 */
	$scope.menuSideNavId = "menuSideNav";

	/*-------------------------------------------------------------------
     * 		 				  	POST CONSTRUCT
     *-------------------------------------------------------------------*/
	
    /*-------------------------------------------------------------------
     * 		 				 	  HANDLERS
     *-------------------------------------------------------------------*/
    /**
     * 
     */
    $scope.toggleMenuSideNavHandler = function() {
    	console.log("toggleMenuSideNavHandler");
    	$mdSidenav($scope.menuSideNavId).toggle();
    };
});

}(window.angular));