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
                views: {
                    'detailView': {
                        templateUrl: basePath + 'viajes.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentViaje = $scope.viajesRecords[$params.viajeId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);



