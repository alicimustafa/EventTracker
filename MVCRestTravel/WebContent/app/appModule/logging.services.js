angular.module('appModule')
.factory('loggingService', function($http){
	var service = {};
	
	service.checkLogin = function(logg){
		return $http({
		      method : 'POST',
		      url : 'api/user/1/todo/',
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : todo
		    });
	}
	return service;
});