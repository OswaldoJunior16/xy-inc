// Statement of application configuration variable
var app = angular.module('enderecamentoapp', [ 'enderecamentoService' ])
		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/home', {
				templateUrl : 'views/enderecamento.html',
				controller : EnderecamentoCtrl
			}).otherwise({
				redirectTo : '/home'
		});
	}
]);