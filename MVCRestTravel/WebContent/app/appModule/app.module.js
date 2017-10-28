angular.module('appModule',['ngRoute'])
.config(function($routeProvider){
	
	$routeProvider
	.when('/',{
		template:'<home><home>'
	})
	.when('/users/:userId/destinations/',{
		template:'<destinations></destinations>'
	})
	
});