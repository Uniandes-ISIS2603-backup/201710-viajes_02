(function (ng) {
    var mod = ng.module("conductorModule", ['ui.router']);
    mod.constant("conductoresContext", "api/Conductores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/conductores/';
            $urlRouterProvider.otherwise("/conductoresList");
            self = this;
             $stateProvider.state('conductores', {
                url: '/conductores',
                abstract: true,
                resolve: {
                    conductores: ['$http','conductoresContext', function ($http, conductoresContext) {
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
            }).state('conductoresList', {
                url: '/list',
                parent: 'conductores',
                views: {
                    'listView': {
                        templateUrl: basePath + 'conductores.list.html'
                    }
                }
            }).state('conductorDetail', {
                url: '/{conductorId:int}/detail',
                parent: 'conductores',
                param: {
                    conductorId: null
                },
                resolve:{
                    currentConductor: ['$http', 'conductoresContext', '$stateParams', function ($http, conductoresContext, $params) {
                            return $http.get(conductoresContext+'/'+$params.conductorId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'conductores.detail.html',
                        controller: ['$scope', 'currentConductor', function ($scope,  currentConductor) {
                                $scope.currentConductor = currentConductor.data;
                            }]
                    }
                }
            }).state('conductoresDetailReview', {
                        url: '/reviews',
                        parent: 'conductorDetail',
                        views: {
                           'detail': {
                                    templateUrl: basePath + 'conductores.detail.reviews.html'
                            }
                        }
             }).state('conductoresDetailAutomoviles', {
                 url: '/automoviles',
                 parent: 'conductorDetail',
                 views: {
                     'detail': {
                         templateUrl: basePath + 'conductores.detail.automoviles.html'
                     }
                 }
             }).state('conductoresAdd', {
                url: '/add',
                parent: 'conductores',
                views: {
                    'listView': {
                        templateUrl: basePath + 'conductores.anadir.html',
                        controller:['$scope', 'conductoresContext', '$http','$state', function ($scope, conductoresContext, $http, $state) {
                                $scope.conductor = {
                                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                                    nombre: '' /*Tipo String*/,
                                    correo: '',
                                    genero: '',
                                    telMovil: '',
                                    edad: '',
                                    imagen: ''
                                    
                                };
                                $scope.submit = function(){
                                    currentConductor = $scope.conductor;
                                    return $http.post(conductoresContext, currentConductor)
                                    .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                                    $state.go('conductoresList');
                                    });
                                };
                            }]
                    }
                }
            });
    }]);
})(window.angular);



