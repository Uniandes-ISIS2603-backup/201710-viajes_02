/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('pagoMultaModule', ['ui.router']);
    mod.constant('pagoMultaContext', "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = "src/modules/PagoMulta/";
            $urlRouterProvider.otherwise("/pagoMultaList");

            $stateProvider.state('pagosMulta', {
                url: 'usuarios/{idUsuario:int}/pagosMulta',
                abstract: true,
                param: {
                    idUsuario: null
                },
                resolve: {
                    multas: ['$http', 'pagoMultaContext', '$stateParams', function ($http, pagoMultaContext, $params) {
                            return $http.get(pagoMultaContext + '/' + $params.idUsuario + '/pagosmulta');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + "pagosMulta.html",
                        controller: ['$scope', 'multas', function ($scope, multas) {
                                $scope.pagosMulta = multas.data;
                            }]
                    }
                }
            }).state('pagosMultaList', {
                url: '/list',
                parent: 'pagosMulta',
                views: {
                    'pagoList': {
                        templateUrl: basePath + "pagosMultaList.html"
                    }
                }
            }).state('pagosMultaDetail', {
                url: '/{pagoId:int}/detail',
                parent: 'pagosMulta',
                param: {
                    pagoId: null
                },
                views: {
                    'pagoDetail': {
                        templateUrl: basePath + "pagosMultaDetail.html",
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentPago = $scope.pagosMulta[$params.pagoId - 1].data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);

