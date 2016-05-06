(function(window, angular) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('authentication', ['ui.router', 'ngMessages', 'ngMaterial', 'eits-md', 'eits-ng' ]);

	/**
	 * 
	 */
	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider, $translateProvider ) {
		//-------
		//Broker configuration
		//-------
		$importServiceProvider.setBrokerURL("./broker/interface");
		//-------
		//Translate configuration
		//-------
		$translateProvider.useURL('./bundles');

		//-------
		//URL Router
		//-------
        $urlRouterProvider.otherwise("/signin");

        //HOME
        $stateProvider.state('authentication',{
        	abstract: true,
        	templateUrl: './modules/authentication/views/index.html'
        })
        .state('signin', {
        	url : "/signin",
        	controller: 'SigninController',
        	templateUrl: './modules/authentication/views/signin/signin-form.html'
        });
	});

	/**
	 * 
	 */
	module.run( function( $rootScope, $window, $state, $stateParams ) {
		$rootScope.$state 		= $state;
		$rootScope.$stateParams = $stateParams;
	});

	/**
	 * 
	 */
	angular.element(document).ready( function() {
		angular.bootstrap( document, ['authentication']);
	});

})(window, window.angular);