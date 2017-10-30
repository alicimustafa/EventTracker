angular.module('appModule')
.component('activities', {
	
	templateUrl: 'app/appModule/activities/activities.component.html',
	controller: function(activityService, $routeParams, $location){
		
		var vm = this;
		vm.userId = $routeParams.userId;
		vm.destId = $routeParams.destId;
		
		vm.activities = [];
		vm.selected = null;
		load();
		
		vm.showEdit = function(activity){
			console.log(activity);
		};
		
		vm.deleteActivity = function(activity){
			console.log(activity);
		}
		
		vm.edit = function(activity){
			activityService.update(vm.userId, vm.destId, activity)
			.then(function(res){
				console.log(res);
				load();
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
		vm.create = function(activity){
			console.log(activity);
			activityService.create(vm.userId, vm.destId, activity)
			.then(function(res){
				console.log(res);
				load();
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
		function load(){
			activityService.index(vm.userId, vm.destId)
			.then(function(res){
				vm.activities = res.data;
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
	},
	controllerAs: 'vm'
});