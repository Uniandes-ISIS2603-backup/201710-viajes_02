(function (ng) {
    var mod = ng.module("viajeroModule", ['ui.router']);
    mod.constant("viajerosContext", "api/viajeros");
    mod.constant("lugarContext", "api/lugares");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Viajero/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/viajerosList");
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('viajeros', {
                // Url que aparecerá en el browser
                url: '/viajeros',
                abstract: true,
                // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                resolve: {
                    viajeros: ['$http', "viajerosContext", function ($http, viajerosContext) {
                            return $http.get(viajerosContext); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }],
                    lugares: ['$http', 'lugarContext', function ($http, lugarContext) {
                            return $http.get(lugarContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viajeros.html',
                        controller: ['$scope', 'viajeros', function ($scope, viajeros) {
                                $scope.viajerosRecords = viajeros.data;
                            }]
                    }
                }
            })
                    .state('viajerosList', {
                        url: '/list',
                        parent: 'viajeros',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'viajeros.list.html'
                            }
                        }
                    })
                    .state('viajeroDetail', {
                        url: '/{viajeroId:int}/detail',
                        parent: 'viajeros',
                        param: {
                            viajeroId: null
                        },
                        resolve: {
                            viajeroActual: ['$http', "viajerosContext", "$stateParams", function ($http, viajerosContext, $params) {
                                    return $http.get(viajerosContext + "/" + $params.viajeroId); // $http retorna una promesa que aquí no se está manejando si viene con error.
                                }]
                        },
                        views: {
                            'listView': {
                                templateUrl: basePath + 'viajeros.list.html'
                            },
                            'detailView': {
                                templateUrl: basePath + 'viajeros.detail.html',
                                controller: ['$scope', 'viajeroActual', 'viajerosContext', '$http', function ($scope, viajeroActual, viajerosContext, $http) {
                                        $scope.currentViajero = viajeroActual.data;
                                        $scope.delete = function () {
                                            $http.delete(viajerosContext + "/" + $scope.currentViajero.id);
                                        };
                                    }]
                            }
                        }
                    })
                    .state('viajerosDetailReview', {
                        url: '/reviews',
                        parent: 'viajeroDetail',
                        views: {
                            'detail': {
                                templateUrl: basePath + 'viajeros.detail.reviews.html'
                            }
                        }
                    })
                    .state('viajerosAdd', {
                        url: '/add',
                        parent: 'viajeros',
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'viajeros.registrar.html',
                                controller: ['$scope', 'viajerosContext', '$http', '$state', 'lugares', function ($scope, viajerosContext, $http, $state, lugares) {
                                        $scope.lugarRecords = lugares.data;
                                        $scope.viajero = {
                                            nombre: '' /*Tipo String*/,
                                            correo: '',
                                            genero: '',
                                            telMovil: '',
                                            edad: '',
                                            imagen: ''
                                        };

                                        $scope.submit = function () {
                                            currentViajero = $scope.viajero;
                                            return $http.post(viajerosContext, currentViajero)
                                                    .then(function () {
                                                        // $http.post es una promesa
                                                        // cuando termine bien, cambie de estado
                                                        $state.go('viajerosList', {}, {reload: true});
                                                    });
                                        };
                                    }]
                            }
                        }
                    })
                    .state('viajerosEdit', {
                        url: '/{viajeroId:int}/edit',
                        parent: 'viajeros',
                        param: {
                            viajeroId: null
                        },
                        resolve: {
                            currentViajero: ['$http', 'viajerosContext', '$stateParams', function ($http, viajerosContext, $params) {
                                    return $http.get(viajerosContext + '/' + $params.viajeroId);
                                }]
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'viajeros.registrar.html',
                                controller: ['$scope', 'viajerosContext', '$http', '$state', 'currentViajero', function ($scope, viajerosContext, $http, $state, currentViajero) {
                                        $scope.viajero = currentViajero.data;
                                        $scope.submit = function () {
                                            currentViajero = $scope.viajero;

                                            return $http.put(viajerosContext + '/' + currentViajero.id, currentViajero)
                                                    .then(function () {
                                                        // $http.post es una promesa
                                                        // cuando termine bien, cambie de estado
                                                        $state.go('viajerosList', {}, {reload: true});
                                                    });
                                        };
                                    }]
                            }
                        }
                    });
        }
    ]);
})(window.angular);