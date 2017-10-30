angular.module('appModule')
.factory('activityService', function($http){
	
	var service = {};
	
	service.index = function(userId, destId){
		return $http({
		      method : 'GET',
		      url : 'rest/users/'+ userId 
		      + '/destinations/' + destId + '/activities'
		    });
	};
	
	service.show = function(userId, destId, activId){
		return $http({
		      method : 'GET',
		      url : 'rest/users/'+ userId 
		      + '/destinations' + destId + '/activities' + activeId
		    });
	};
	
	service.destroy = function(userId, destId, activeId){
		return $http({
		      method : 'DELETE',
		      url : 'rest/users/'+ userId 
		      + '/destinations/' + destId + '/activities/' + activeId
		    });
	};
	
	service.create = function(userId, destId, destination){
		return $http({
		      method : 'POST',
		      url : 'rest/users/'+ userId 
		      +'/destinations/' + destId  +'/activities',
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : destination
		    });
	};
	
	service.update = function(userId, destId, destination){
		return $http({
		      method : 'PUT',
		      url : 'rest/users/' + userId 
		      + '/destinations/' + destId + '/activities/' + destination.id,
		      headers : {
		          'Content-Type' : 'application/json'
		        },
		      data : destination
		    });
	};
	
	return service;

});