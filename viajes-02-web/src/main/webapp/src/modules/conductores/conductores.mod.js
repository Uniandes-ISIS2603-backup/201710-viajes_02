(function (ng) {
    var mod = ng.module("conductorModule", ['ui.router']);
    mod.constant("conductoresContext", "api/conductores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/conductores/';
            $urlRouterProvider.otherwise("/conductoresList");
            self = this;
             $stateProvider.state('conductores', {
                url: '/conductores',
                abstract: true,
                resolve: {
                    conductores: ['$http', function ($http) {
                            return $http.get('data/conductores.json');
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
                views: {
                    'detailView': {
                        templateUrl: basePath + 'conductores.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentConductor = $scope.conductoresRecords[$params.conductorId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
