angular.module('appModule')
.component('destinations',{
	templateUrl:'app/appModule/destinations/destinations.component.html',
	controller: function(destService){
		
		var vm = this;
		
		vm.destList = [];
		
		var getList = function(){
			
		};
		
		var figureCost = function(activities){
			return activities.reduce(function(sum, activity) {
				  return sum + activity.cost;
			}, 0);
		}
	},
	
	controllerAs:'vm'
})