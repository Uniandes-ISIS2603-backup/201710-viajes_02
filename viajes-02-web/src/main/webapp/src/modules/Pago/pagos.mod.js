/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);
    mod.constant("pagosContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/Pago/';

            $urlRouterProvider.otherwise("/pagosList");

            $stateProvider.state('pagos', {
                url: '/usuarios/{idUsuario:int}/pagos',
                abstract: true,
                param: {
                  idUsuario: null
                },
                resolve: {
                    pagos: ['$http', 'pagosContext', '$stateParams', function ($http, pagosContext, $params) {
                            return $http.get(pagosContext + '/' + $params.idUsuario + '/pagos');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.html',
                        controller: ['$scope', 'pagos', function ($scope, pagos) {
                                $scope.pagosRecords = pagos.data;
                            }]
                    }
                }
            }).state('pagosList', {
                url: '/list',
                parent: 'pagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pagos.list.html'
                    }
                }
            }).state('pagosDetail', {
                url: '/{pagoId:int}/detail',
                parent: 'pagos',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'pagos.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentPago = $scope.pagosRecords[$params.pagoId - 1];
                            }]
                    }
                }
            }).state('pagoUserList', {
                url: '/{usuarioId:int}/pagosUsuario',
                parent: 'pagos',
                param: {
                    usuarioId: null
                },
                views: {
                    'userPagoView': {
                        templateUrl: basePath + 'pago.user.list.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                var arreglo = [];
                                var i;
                                for (i = 0; i < $scope.pagosRecords.length; i++) {
                                    if ($scope.pagosRecords[i].usuarioRemitente.id === $params.usuarioId) {
                                        arreglo.push($scope.pagosRecords[i]);
                                    }
                                }
                                $scope.userPagos = arreglo;
                            }]
                    }
                }
            });
        }
    ]);
})(window.angular);