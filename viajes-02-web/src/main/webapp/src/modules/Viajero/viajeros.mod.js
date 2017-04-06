(function (ng) {
    var mod = ng.module("viajeroModule", ['ui.router']);
    mod.constant("viajerosContext", "api/viajeros");
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
                    viajeros: ['$http', "viajerosContext",function ($http, viajerosContext) {
                            return $http.get(viajerosContext); // $http retorna una promesa que aquí no se está manejando si viene con error.
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
                        views: {
                            'listView': {
                                templateUrl: basePath + 'viajeros.list.html'
                            },
                            'detailView': {
                                templateUrl: basePath + 'viajeros.detail.html',
                                controller: ['$scope', '$stateParams', function ($scope, $params) {
                                        $scope.currentViajero = $scope.viajerosRecords[$params.viajeroId - 1];
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
                    });
        }
    ]);
})(window.angular);