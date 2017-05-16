(function (ng) {
    var mod = ng.module("viajeModule", ['ui.router']);
    mod.constant("viajesContext", "api/viajes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/viajes/';
            $urlRouterProvider.otherwise("/viajesList");
            self = this;
             $stateProvider.state('viajes', {
                url: '/viajes',
                abstract: true,
                resolve: {
                    viajes: ['$http', 'viajesContext',function ($http, viajesContext) {
                            return $http.get(viajesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viajes.html',
                        controller: ['$scope', 'viajes', function ($scope, viajes) {
                                $scope.viajesRecords = viajes.data;
                            }]
                    }
                }
            }).state('viajesList', {
                url: '/list',
                parent: 'viajes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'viajes.list.html'
                    }
                }
            }).state('viajeDetail', {
                url: '/{viajeId:int}/detail',
                parent: 'viajes',
                param: {
                    viajeId: null
                },
                resolve:{
                    currentViaje: ['$http', 'viajesContext', '$stateParams', function ($http, viajesContext, $params) {
                            return $http.get(viajesContext+'/'+$params.viajeId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'viajes.detail.html',
                        controller: ['$scope', 'currentViaje', function ($scope,  currentViaje) {
                                $scope.currentViaje = currentViaje.data;
                            }]
                    }
                }
            }).state('viajeAdd', {
                url: '/{conductorId:int}/addViaje',
                parent: 'viajes',
                param: {
                    conductorId: null
                },
                resolve:{
                    currentConductor: ['$http', '$stateParams', function ($http, $params) {
                            return $http.get("api/Conductores"+'/'+$params.conductorId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'viajes.anadir.html',
                        controller: ['$scope', 'currentConductor', '$http', '$state', 'viajesContext', function ($scope,  currentConductor, $http, $state, viajesContext) {
                                $scope.currentConductor = currentConductor.data;
                                $scope.automoviles = $scope.currentConductor.automoviles;
                                $scope.placa = '';
                                $scope.origen = {
                                    id: Math.floor((Math.random() * 100000) + 1),
                                    direccion: '',
                                    lat: '',
                                    lon: '',
                                    lugar: ''
                                };
                                $scope.destino = {
                                    id: Math.floor((Math.random() * 100000) + 1),
                                    direccion: '',
                                    lat: '',
                                    lon: '',
                                    lugar: ''
                                };
                                $scope.viaje ={
                                    idViaje: undefined,
                                    automovil: '',
                                    conductor: '',
                                    origen: '',
                                    destino: '',
                                    diaYHoraLlegada: '',
                                    diaYHoraPartida: '',
                                    gastosCompartidos: '',
                                    kilometros: ''
                                };
                                setAuto = function(placa){
                                    respuesta = {
                                        placa : '',
                                        numSeguro : '',
                                        modelo : '',
                                        marca : '',
                                        compSeguros : '',
                                        color : '',
                                        cantAsientos : '',
                                        id: ''
                                    };
                                    automoviles = $scope.automoviles;
                                    for(var j=0; j < automoviles.length; j++){
                                        automovil = automoviles[j];
                                        pplaca = automovil.placa;
                                        if(placa.includes(pplaca)){
                                            respuesta.placa = automovil.placa;
                                            respuesta.numSeguro = automovil.numSeguro;
                                            respuesta.modelo = automovil.modelo;
                                            respuesta.marca = automovil.marca;
                                            respuesta.compSeguros = automovil.compSeguros;
                                            respuesta.color = automovil.color;
                                            respuesta.cantAsientos = automovil.cantAsientos;
                                            respuesta.id = automovil.id;
                                            return respuesta;
                                            
                                        }
                                    }
                                };
                                dateToJson = function(date){
                                var year = date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9) ;
                                var month = date.charAt(3) + date.charAt(4);
                                var day = date.charAt(1) + date.charAt(0);
                                var json = year +'-'+month+'-'+day+'T01:01:05.902Z';
                                    
                                    
                                    return json;
                                    
                                };
                                $scope.submit = function(){
                                    viaje = $scope.viaje;
                                    viaje.origen = $scope.origen;
                                    viaje.destino = $scope.destino;
                                    viaje.conductor = currentConductor.data;
                                    viaje.automovil = setAuto($scope.placa);
                                    viaje.diaYHoraPartida = dateToJson(viaje.diaYHoraPartida);
                                    viaje.diaYHoraLlegada = dateToJson(viaje.diaYHoraLlegada);
                                    console.log(viaje);
                                    return $http.post(viajesContext, viaje)
                                    .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                                    $state.go('viajesList',{}, {reload: true});
                                    });
                                };
                            }]
                    }
                }
            });;
        }]);
})(window.angular);