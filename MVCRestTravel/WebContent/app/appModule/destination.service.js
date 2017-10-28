angular.module('appModule')
.factory('destService',function($http){
	
	var service = {};
	
	service.index = function(userId){
		return $http({
		      method : 'GET',
		      url : 'rest/users/'+ userId +'/destinations'
		    });
	};
	
	service.show = function(userId, destId){
		return $http({
		      method : 'GET',
		      url : 'rest/users/'+ userId +'/destinations' + destId
		    });
	};
	
	service.destroy = function(){
		return $http({
		      method : 'DELETE',
		      url : 'rest/users/'+ userId +'/destinations' + destId
		    });
	};
	
	service.create = function(userId, destination){
		return $http({
		      method : 'POST',
		      url : 'rest/users/'+ userId +'/destinations',
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : destination
		    });
	};
	
	service.update = function(userId, destination){
		return $http({
		      method : 'POST',
		      url : 'rest/users/'+ userId +'/destinations' + destination.id,
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : destination
		    });
	};
	
	return service;
	
});