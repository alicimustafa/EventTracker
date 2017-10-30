angular.module('appModule')
.component('activities', {
	
	templateUrl: 'app/appModule/activities/activities.component.html',
	controller: function(activityService, $routeParams, $location){
		
		var vm = this;
		vm.userId = $routeParams.userId;
		vm.destId = $routeParams.destId;
		
		vm.activities = [];
		vm.selected = null;
		vm.edit = false;
		load();
		
		vm.showView = function(activity){
			vm.selected = activity;
		}
		
		vm.showTable = function(){
			vm.selected = null;
		}
		
		vm.showEdit = function(){
			vm.edit = true;
		};
		
		vm.cancelEdit = function(){
			vm.edit = false;
		}
		
		vm.deleteActivity = function(activity){
			console.log(activity);
			activityService.destroy(vm.userId, vm.destId, activity.id)
			.then(function(res){
				load();
			})
			.catch(function(err){
				console.log(err);
			});
		}
		
		vm.update = function(){
			activityService.update(vm.userId, vm.destId, vm.selected)
			.then(function(res){
				load();
				vm.edit = false;
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
		vm.create = function(activity){
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