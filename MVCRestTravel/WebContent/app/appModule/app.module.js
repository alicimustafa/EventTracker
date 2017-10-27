angular.module('appModule',['ngRoute'])
.config(function($routeProvider){
	
	$routeProvider
	.when('/',{
		template:'<home><home>'
	})
	
});