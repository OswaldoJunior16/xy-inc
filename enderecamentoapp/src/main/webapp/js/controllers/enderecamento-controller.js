// Controller of view
function EnderecamentoCtrl($scope, $http, Enderecamento) {

	// Initializes the variables
	init();
	
	// Search service request
	$scope.buscar = function() {
		init();
		$http({
			method : 'GET',
			url : 'rest/buscaCepLogradouro/' + $scope.enderecamento
		}).then(function successCallback(response) {
			$scope.enderecamentos = response.data;
			if($scope.enderecamentos.length == 0){
				showModal();
			}
		}, function errorCallback(response) {
			$scope.errorMessages = true;
            $scope.$apply();
		});
	
	};
	
	// Show modal of zero result
	function showModal(){
		$('#modal_result_zero').modal('show');
	}
	
	// Initializes the variables
	function init(){
		$scope.enderecamentos = {};
		$scope.errorMessages = false;
	    $scope.showDica = false;
	}

}