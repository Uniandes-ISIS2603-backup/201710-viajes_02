(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies 
        'cobroModule',
        'pagoModule',
        'lugarModule',
        'automovilesModule',
        //'reviewModule',
        'reservaModule',
        'viajeroModule',
        'conductorModule',
        'viajeModule',
        'homeModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);    
 