/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng) {
    
    var mod = ng.module("multaModule", ['ui.router']);
    
    mod.constant("multaContext", "api/usuarios/1/cobros");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/CobroMulta/';
        
        $urlRouterProvider.otherwise("/cobroList");
        
        $stateProvider.state('multas', {
            url: '/multas',
            abstract: true,
            resolve: {
                multas: ['$http', 'multaContext' , function($http, multaContext) {
                        return $http.get(multaContext);
                }]
            },
            views: {
                'mainView': {
                     templateUrl: basePath + 'multas.html',
              controller: ['$scope', 'multas', function($scope, multas) {
                        $scope.todasMultas = multas.data;
                }]
              }
            }  
        }).state('multaList', {
            url: '/multaList',
            parent: 'multas',
            views: {
                'multaList': {
                    templateUrl: basePath +"multasList.html",
                }
            }
        }).state('multaDetail', {
           url: '/{multaId:int}/multaDetail',
           parent: 'multas',
           param: {
               multaId: null
           },
           resolve: {
               currentMulta: ['multaContext', '$http', '$stateParams', function(multaContext, $http, $params) {
                       return $http.get(multaContext +"/" +$params.multaId);
               }]
           }, 
           views: {
               'detailView': {
                   templateUrl: basePath +"multasDetail.html",
                   controller: ['currentMulta', '$scope', function(currentMulta, $scope) {
                           $scope.multaActual = currentMulta.data;
                   }]
               }
           }
        });
    }]);
    
})(window.angular);
