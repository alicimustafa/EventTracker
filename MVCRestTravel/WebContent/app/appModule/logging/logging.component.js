angular.module('appModule')
.component('logging',{
	templateUrl:'app/appModule/logging/logging.component.html',
	controller: function(loggingService, $location){
		
		var vm = this;
		
		vm.loggingVal = 'Logg in';
		vm.loggedIn = false;
		vm.createAccount = false;
		vm.errorMessage = "";
		
		vm.checkLogging = function(log){
			if(!vm.loggedIn){
				loggingService.checkLogin(log)
				.then(function(res){
					vm.loggedIn = true;
					vm.loggingVal = 'Logg off';
					vm.errorMessage = "";
					$location.path('users/'+res.data.id + '/destinations/');
				})
				.catch(function(err){
					console.log(err);
					vm.errorMessage = "Your Information is incorrect";
				});
			} else {
				vm.loggedIn = false;
				vm.loggingVal = 'Logg in';
			}
		}
		
		vm.createFormShow = function(){
			vm.createAccount = true;
		};
		
		vm.cancelAcc = function(){
			vm.createAccount = false;
		};
		
		vm.createAcc = function(account){
			console.log(account)
			loggingService.create(account)
			.then(function(res){
				vm.createAccount = false;
			})
			.catch(function(err){
				vm.errorMessage = "Ther was a problem creating Account";
				vm.createAccount = false;
			});
		}
	},
	controllerAs: 'vm'
});