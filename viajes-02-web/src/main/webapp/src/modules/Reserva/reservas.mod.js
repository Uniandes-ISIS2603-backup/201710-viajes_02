/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reservaModule", ['ui.router']);
    mod.constant("reservasContext", "api/viajeros");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/Reserva/';
            $urlRouterProvider.otherwise("/reservasList");

            $stateProvider.state('reservas', {
                url: 'viajeros/{idViajero:int}/reservas/',
                abstract: true,
                param: {
                    idViajero: null
                },
                resolve: {
                    reservas: ['$http', 'reservasContext', '$stateParams', function ($http, reservasContext, $params) {
                            return $http.get(reservasContext + '/' + $params.idViajero + '/reservas');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reservas.html',
                        controller: ['$scope', 'reservas', function ($scope, reservas) {
                                $scope.reservasRecords = reservas.data;
                            }]
                    }
                }
            }).state('reservasList', {
                url: '/list',
                parent: 'reservas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reservas.list.html'
                    }
                }
            }).state('reservasDetail', {
                url: '/{idReserva:int}',
                parent: 'reservas',
                param: {
                    idReserva: null
                },
                resolve: {
                    currentReserva: ['$http', 'reservasContext', '$stateParams', function ($http, reservasContext, $params) {
                            return $http.get(reservasContext + '/' + $params.idViajero + '/reservas/' + $params.idReserva);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'reservas.detail.html',
                        controller: ['$scope', 'currentReserva', function ($scope, currentReserva) {
                                $scope.currentReserva = currentReserva.data;
                            }]
                    }
                }
            });
        }
    ]);
})(window.angular);