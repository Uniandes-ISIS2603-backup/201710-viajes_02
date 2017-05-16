(function (ng) {
    var mod = ng.module("conductorModule", ['ui.router']);
    mod.constant("conductoresContext", "api/Conductores");
    mod.constant("automovilContext", "api/automoviles");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/conductores/';
        $urlRouterProvider.otherwise("/conductoresList");
        self = this;
        $stateProvider.state('conductores', {
            url: '/conductores',
            abstract: true,
            resolve: {
                conductores: ['$http', 'conductoresContext', function ($http, conductoresContext) {
                    return $http.get(conductoresContext);
                }]
            },
            views: {
                'mainView': {
                    templateUrl: basePath + 'conductores.html',
                    controller: ['$scope', 'conductores', function ($scope, conductores) {
                        $scope.conductoresRecords = conductores.data;
                    }]
                }
            }
        })
            .state('conductoresList', {
            url: '/list',
            parent: 'conductores',
            views: {
                'listView': {
                    templateUrl: basePath + 'conductores.list.html'
                }
            }
        })
            .state('conductorDetail', {
            url: '/{conductorId:int}/detail',
            parent: 'conductores',
            param: {
                conductorId: null
            },
            resolve: {
                currentConductor: ['$http', 'conductoresContext', '$stateParams', function ($http, conductoresContext, $params) {
                    return $http.get(conductoresContext + '/' + $params.conductorId);
                }]
            },
            views: {
                'detailView': {
                    templateUrl: basePath + 'conductores.detail.html',
                    controller: ['$scope', 'currentConductor', function ($scope, currentConductor) {
                        $scope.currentConductor = currentConductor.data;
                    }]
                
                },
                'listView': {
                        templateUrl: 'src/modules/viajes/' + 'viajes.list.html',
                        controller: ['$scope', 'currentConductor', function ($scope, currentConductor) {
                                $scope.viajesRecords = currentConductor.data.viajes;
                            }]
                    }
            }
        })
            .state('conductoresDetailReview', {
            url: '/reviews',
            parent: 'conductorDetail',
            views: {
                'detail': {
                    templateUrl: basePath + 'conductores.detail.reviews.html'
                }
            }
        })
            .state('conductoresDetailAutomoviles', {
            url: '/automoviles',
            parent: 'conductorDetail',
            views: {
                'detail': {
                    templateUrl: basePath + 'conductores.detail.automoviles.html'
                }
            }
        })
            .state('conductoresAdd', {
            url: '/add',
            parent: 'conductores',
            views: {
                'listView': {
                    templateUrl: basePath + 'conductores.anadir.html',
                    controller: ['$scope', 'conductoresContext', '$http', '$state', function ($scope, conductoresContext, $http, $state) {
                        $scope.conductor = {
                            id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                            nombre: '' /*Tipo String*/,
                            correo: '',
                            genero: '',
                            telMovil: '',
                            edad: '',
                            imagen: ''

                        };
                        $scope.submit = function () {
                            currentConductor = $scope.conductor;
                            return $http.post(conductoresContext, currentConductor)
                                .then(function () {
                                    // $http.post es una promesa
                                    // cuando termine bien, cambie de estado
                                    $state.go('conductoresList',{}, {reload: true});
                                });
                        };
                    }]
                }
            }
        })
            .state('reviewsFormulario', {
            url: '/addReview',
            parent: 'conductorDetail',
            views: {
                'detail': {
                    templateUrl: basePath + 'reviews.formulario.html',
                    controller: ['$scope', '$http', 'conductoresContext',
                        function ($scope, $http, conductoresContext) {
                            var valor = 0;
                            $scope.agregarRev = function () {
                                if (document.getElementById('radio1'))
                                    valor = 1;
                                else if (document.getElementById('radio2'))
                                    valor = 2;
                                else if (document.getElementById('radio3'))
                                    valor = 3;
                                if (document.getElementById('radio4'))
                                    valor = 4;
                                else
                                    valor = 5;
                                var comment = document.getElementById('comment').value;
                                var idCalificado = $scope.currentConductor.id;
                                var idCalificador = document.getElementById('comment').value;

                                var review = {
                                    calificacion: valor,
                                    coment: comment,
                                    idCalificado: idCalificado,
                                    idCalificador: idCalificador
                                };

                                $http.post('api/usuarios/' + $scope.currentConductor.id + '/reviews', review)
                            }
                        }]
                }
            }
        })
            .state('automovilesFormulario', {
                url: '/addAuto',
                parent: 'conductorDetail',
                views: {
                    'detail': {
                        templateUrl: basePath + 'automoviles.formulario.html',
                        controller: ['$scope', '$http', 'conductoresContext',
                            function ($scope, $http, conductoresContext) {

                                $scope.agregar = function () {

                                    var placa = document.getElementById('placa').value;
                                    var color = document.getElementById('color').value;
                                    var marca = document.getElementById('marca').value;
                                    var modelo = document.getElementById('modelo').value;
                                    var aseg = document.getElementById('aseguradora').value;
                                    var numseg = document.getElementById('numseguro').value;
                                    var cantasientos = document.getElementById('cantasientos').value;
                                    var carrito = {
                                        marca: marca,
                                        modelo: modelo,
                                        cantAsientos: cantasientos,
                                        color: color,
                                        compSeguros: aseg,
                                        numSeguro: numseg,
                                        placa: placa,
                                        conductorDTO: {
                                            id: $scope.currentConductor.id
                                        }
                                    };

                                    $http.post('api/automoviles', carrito)
                                }
                            }]
                    }
                }
            })
            .state('automovilesDetail', {
                url: '/{autoId:int}/AutoDetail',
                parent: 'conductoresDetailAutomoviles',
                param: {
                    autoId: null
                },
                resolve: {
                    currentAutomovil: ['$http', 'automovilContext', '$stateParams',
                        function ($http, automovilContext, $params) {
                            return $http.get(automovilContext + '/' + $params.autoId);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'automoviles.list.html',
                        controller: ['$scope', 'currentAutomovil', function ($scope, currentAutomovil) {
                            $scope.currentAutomovil = currentAutomovil.data;
                        }]
                    }
                }
            });
    }])
})
(window.angular);



