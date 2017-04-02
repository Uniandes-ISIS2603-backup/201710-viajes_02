(function (ng) {
    var mod = ng.module("lugarModule", ['ui.router']);
    mod.constant("lugarContext", "api/lugares");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Lugar/';
            $urlRouterProvider.otherwise("/lugarList");

            $stateProvider.state('lugares', {
                url: '/lugares',
                abstract: true,
                resolve: {
                    lugares: ['$http','lugarContext', function ($http, lugarContext) {
                            return $http.get('data/lugares.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'lugar.html',
                        controller: ['$scope', 'lugares', function ($scope, lugares) {
                                $scope.lugarRecords = lugares.data;
                            }]
                    }
                }
            }).state('lugarList', {
                url: '/list',
                parent: 'lugares',
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugar-list.html'
                    }
                }
            }).state('lugarDetail', {
                url:'/{lugarId:int}/detail',
                parent: 'lugares',
                param: {
                   lugarId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'lugar-detail.html',
                        controller: ['$scope', '$stateParams', function($scope, $params) {
                                $scope.currentLugar = $scope.lugarRecords[$params.lugarId-1]
                        }]
                    }
                } 
            });
        }]);
})(window.angular);