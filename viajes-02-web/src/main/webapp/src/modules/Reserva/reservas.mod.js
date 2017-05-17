(function (ng) {
    var mod = ng.module("reservaModule", ['ui.router']);
    mod.constant("reservasContext", "api/viajeros");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/Reserva/';
            $urlRouterProvider.otherwise("/reservasList");
            self = this;
            $stateProvider.state('reservas', {
                url: 'viajeros/{idViajero:int}/reservas',
                abstract: true,
                param: {
                    idViajero: null
                },
                resolve: {
                    reservas: ['$http', 'reservasContext', '$stateParams', function ($http, reservasContext, $params) {
                            return $http.get(reservasContext + '/' + $params.idViajero + '/reservas');
                        }],
                    currentViajero: ['$http', 'reservasContext', '$stateParams',  function ($http, reservasContext, $params) {
                            return $http.get(reservasContext + '/' + $params.idViajero);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reservas.html',
                        controller: ['$scope', 'reservas', 'currentViajero', function ($scope, reservas, currentViajero) {
                                $scope.reservasRecords = reservas.data;
                                $scope.currentViajero = currentViajero.data;
                                console.log($scope.reservasRecords);
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
//              .state('reservar', {
//                url: 'viajeros/{idViajero:int}/reservas/list/reservar',
//                parent: 'reservas',
//                views: {
//                    'createView': {
//                        templateUrl: basePath + 'reservar.html',
//                        controller: ['$scope', '$http', 'reservasContext', 'currentViajero', function($scope, $http, reservasContext, currentViajero) {
//                                
//                                $scope.currentViajero = currentViajero.data;
//                                
//                                $scope.buscarviajes = function() {
//                                    var origen = document.getElementById("desde").value;
//                                    var destino = document.getElementById("hasta").value;
//                                    var fechasalida = document.getElementById("fechasalida").value;
//                                    
//                                    $scope.viajes = $http.get('api/viajes/' + origen + ";" + destino);
//                                };
//                                
//                                $scope.reservar = function() {
//                                    $scope.reserva = {
//                                        id:undefined,
//                                        precio:'',
//                                        valorComision:'',
//                                        puestosReservados:'',
//                                        viaje: {
//                                            
//                                        },
//                                        viajero = $scope.currentViajero
//                                    };
//                                    
//                                    $http.post(reservasContext, reserva);
//                                };
//                        }]
//                    }
//                }
//            });
        }]);
})(window.angular);