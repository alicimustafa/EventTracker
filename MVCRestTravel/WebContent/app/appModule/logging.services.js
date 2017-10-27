angular.module('appModule')
.factory('loggingService', function($http){
	var service = {};
	
	service.checkLogin = function(log){
		return $http({
		      method : 'POST',
		      url : 'rest/login',
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : log
		    });
	}
	
	service.create = function(account){
		return $http({
		      method : 'POST',
		      url : 'rest/users',
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : account
		    });
	}
	return service;
});