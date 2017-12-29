angular.module('appModule')
.factory('loggingService', function($http){
	var service = {};
	
	service.checkLogin = function(log){
		return $http({
		      method : 'POST',
		      url : 'rest/auth/login',
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : log
		    });
	}
	
	service.logout = function(){
		return $http({
			method : 'POST',
			url : 'rest/auth/logout',
			header : {
				'Content-Type' : 'application/json'
			}
		});
	}
	
	service.create = function(account){
		return $http({
		      method : 'POST',
		      url : 'rest/auth/register',
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : account
		    });
	}
	return service;
});