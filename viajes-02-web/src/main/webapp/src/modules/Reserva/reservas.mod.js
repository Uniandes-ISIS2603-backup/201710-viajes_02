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
                        controller: ['$scope', '$http', 'reservasContext', 'currentViajero', 'viajes', function ($scope, $http, reservasContext, currentViajero, viajes) {
                                
                                $scope.currentViajero = currentViajero.data;
                                $scope.viajes = viajes.data;
                                
                                if($scope.reservasRecords.length > 0) {
                                    document.getElementById('reservas-viajero').style.display = "block";
                                }
                                
                                $scope.buscarviajes = function() {
                                    var origen = document.getElementById('desde').value;
                                    var destino = document.getElementById('hasta').value;
                                    
                                    if (origen && destino) {
                                        document.getElementById('resultado').style.display = "block";
                                    }
                                };
                                
                                $scope.reservar = function(id) {
                                    
                                    var viaje = $scope.viajes[id-1];
                                    var currentViajero = $scope.currentViajero;
                                    var precio = (viaje.gastosCompartidos)*(4);
                                    
                                    var e = document.getElementById("cantAsientos" + id);
                                    var cantAsientos = e.options[e.selectedIndex].text;
                                    
                                    var reserva = {
                                        "precio":precio,
                                        "valorComision":(precio)*(0.1),
                                        "puestosReservados":cantAsientos,
                                        "viajero": {
                                            "id": currentViajero.id,
                                            "nombre": currentViajero.nombre,
                                            "genero": currentViajero.genero,
                                            "telMovil": currentViajero.telMovil,
                                            "edad": currentViajero.edad,
                                            "rating": currentViajero.rating,
                                            "correo": currentViajero.correo,
//                                            "direccion": {
//                                                "id": currentViajero.direccion.id,
//                                                "lugar": currentViajero.direccion.lugar,
//                                                "direccion": currentViajero.direccion.direccion,
//                                                "lat": currentViajero.direccion.lat,
//                                                "lon": currentViajero.direccion.lon,
//                                                "rutaImagen": currentViajero.direccion.rutaImagen
//                                            },
                                            "imagen": currentViajero.imagen
                                        },
                                        "viaje": {
                                            "idViaje": viaje.idViaje,
                                            "Kilometros": viaje.Kilometros,
                                            "gastosCompartidos": viaje.gastosCompartidos,
                                            "diaYHoraPartida": viaje.diaYHoraPartida,
                                            "diaYHoraLlegada": viaje.diayHoraLlegada,
                                            "origen": {
                                                "id": viaje.origen.id,
                                                "lugar": viaje.origen.lugar,
                                                "direccion": viaje.origen.direccion,
                                                "lat": viaje.origen.lat,
                                                "lon": viaje.origen.lon,
                                                "rutaImagen": viaje.origen.rutaImagen
                                            },
                                            "destino": {
                                                "id": viaje.destino.id,
                                                "lugar": viaje.destino.lugar,
                                                "direccion": viaje.destino.direccion,
                                                "lat": viaje.destino.lat,
                                                "lon": viaje.destino.lon,
                                                "rutaImagen": viaje.destino.rutaImagen
                                            },
                                            "automovil": {
                                                "id": viaje.automovil.id,
                                                "placa": viaje.automovil.placa,
                                                "marca": viaje.automovil.marca,
                                                "modelo": viaje.automovil.modelo,
                                                "cantAsientos": viaje.automovil.cantAsientos,
                                                "color": viaje.automovil.color,
                                                "compSeguros": viaje.automovil.compSeguros,
                                                "numSeguro": viaje.automovil.numSeguro,
                                            },
                                            "conductor": {
                                                "id": viaje.conductor.id,
                                                "nombre": viaje.conductor.nombre,
                                                "genero": viaje.conductor.genero,
                                                "telMovil": viaje.conductor.telMovil,
                                                "edad": viaje.conductor.edad,
                                                "rating": viaje.conductor.rating,
                                                "correo": viaje.conductor.correo,
                                                "imagen": viaje.conductor.imagen
                                            }
                                        }
                                    };
                                    
                                    console.log(reserva);
                                    
                                    $http.post(reservasContext + '/'+ $scope.currentViajero.id +'/reservas', reserva)
                                            .then(function (response) {
                                                window.location.reload();
                                            },function (response) {
                                                        console.log("fallo");
                                    });
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
                        controller: ['$scope', '$http', '$state', 'currentReserva', 'reservasContext', function ($scope, $http, $state, currentReserva, reservasContext) {
                                $scope.currentReserva = currentReserva.data;
                                console.log($scope.currentReserva);
                                $scope.eliminar = function() {
                                    $http.delete(reservasContext + '/' + $scope.currentReserva.viajero.id + '/reservas/' + $scope.currentReserva.id).then(
                                            function () {
                                                $state.go('reservasList', {}, {reload: true});
                                            });
                                };
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