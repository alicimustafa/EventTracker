angular.module('appModule')
.component('destinations',{
	templateUrl:'app/appModule/destinations/destinations.component.html',
	controller: function(destService, $routeParams, $location){
		
		var vm = this;
		
		vm.destList = [];
		vm.userId = $routeParams.userId;
		vm.selected = null;
		vm.edit = false;
		getList();
		
		vm.getTotal = function(){
			return vm.destList.reduce(function(total, val){
				return total + val.cost;
			}, 0);
		};
		
		vm.showView = function(dest){
			vm.selected = angular.copy(dest);
		};
		
		vm.showTable = function(){
			vm.selected = null;
		};
		
		vm.showEdit = function(){
			vm.edit = true;
		};
		
		vm.cancelEdit = function(){
			vm.edit = false;
		};
		
		vm.deleteDest = function(dest){
			destService.destroy(vm.userId, dest.id)
			.then(function(des){
				getList();
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
		vm.updateDest = function(){
			var destination = {
				name: vm.selected.name,
				imgUrl: vm.selected.imgUrl
			};
			destService.update(vm.userId, vm.selected.id, destination)
			.then(function(res){
				vm.selected = null;
				vm.edit = false;
				getList();
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
		vm.createDest = function(dest){
			destService.create(vm.userId, dest)
			.then(function(res){
				getList();
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
		function getList(){
			destService.index($routeParams.userId)
			.then(function(res){
				vm.destList = [];
				res.data.forEach(function(val){
					var dest = {};
					dest.id = val.id;
					dest.name = val.name;
					dest.imgUrl = val.imgUrl;
					dest.cost = figureCost(val.activities);
					vm.destList.push(dest);
				});
			})
			.catch(function(err){
				console.log(err);
			});
		};
		
		function figureCost(activities){
			return activities.reduce(function(sum, activity) {
				  return sum + activity.cost;
			}, 0);
		};
	},
	
	controllerAs:'vm'
})