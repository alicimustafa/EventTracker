angular.module('appModule')
.component('logging',{
	templateUrl:'app/appModule/logging/logging.component.html',
	controller: function(){
		
		var vm = this;
		
		vm.loggingVal = 'Logg in';
	},
	controllerAs: 'vm'
});