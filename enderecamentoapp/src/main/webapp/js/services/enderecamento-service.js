// Define the REST resource service, allowing us to interact with it as a high level service
angular.module('enderecamentoService', ['ngResource']).
    factory('Enderecamento', function($resource){    	
    	return $resource('rest/buscaCepLogradouro:cepLogradouro', {});
});
