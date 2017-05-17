(function (ng) {
    var mod = ng.module("reservaModule", ['ui.router']);
    mod.constant("reservasContext", "api/viajeros");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/Reserva/';
            $urlRouterProvider.otherwise("/reservasList");
            self = this;
            $stateProvider.state('reservas', {
                url: '/viajeros/{idViajero:int}/reservas',
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
                url: '',
                parent: 'reservas',
                resolve: {
                    currentViajero: ['$http', 'reservasContext', '$stateParams', function ($http, reservasContext, $params) {
                            return $http.get(reservasContext + '/' + $params.idViajero);
                        }],
                    viajes: ['$http', function ($http) {
                            return $http.get('api/viajes');
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'reservas.list.html',
                        controller: ['$scope', '$http', 'currentViajero', 'viajes', function ($scope, $http, currentViajero, viajes) {
                                $scope.currentViajero = currentViajero.data;
                                $scope.viajes = viajes.data;
                                
                                $scope.buscarviajes = function() {
                                    $('#busqueda').addClass('resultado');
                                    console.log($scope.viajes);
                                };
                                
                                $scope.reservar = function(id) {
                                    
                                    console.log($scope.viajes[id-1]);
                                    var e = document.getElementById("cantAsientos" + id);
                                    var option = e.options[e.selectedIndex].text;
                                    console.log(option);
//                                    var reserva = {
//                                        "id":undefined,
//                                        "precio":'',
//                                        "valorComision":'',
//                                        "puestosReservados":option,
//                                        "viaje": {
//                                            
//                                        },
//                                        "viajero": {
//                                            "id":$scope.currentViajero.id
//                                        }
//                                    };
//                                    
//                                    $http.post('api/viajeros/', reserva);
                                };
                            }]
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
                                console.log($scope.currentReserva);
                            }]
                    }
                }
            });
        }]);
    
//    mod.controller('reservar', ['$scope', '$http', 'reservasContext', function($scope, $http, reservasContext) {
//                                
//                                $scope.buscarviajes = function() {
////                                    var origen = document.getElementById("desde").value;
////                                    var destino = document.getElementById("hasta").value;
//                                    console.log($scope.viajes);
//                                };
//                                
//                                $scope.reservar = function() {
//                                    var reserva = {
//                                        "id":undefined,
//                                        "precio":'',
//                                        "valorComision":'',
//                                        "puestosReservados":'',
//                                        "viaje": {
//                                            
//                                        },
//                                        "viajero": {
//                                            
//                                        }
//                                    };
//                                    
//                                    $http.post(reservasContext, reserva);
//                                };
//    }]);
})(window.angular);

//function buscarviajes() {
//    $('#busqueda').addClass("resultado");
//}