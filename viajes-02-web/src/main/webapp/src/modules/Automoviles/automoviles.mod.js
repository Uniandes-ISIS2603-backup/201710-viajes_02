/**
 * Created by Danny on 1/04/2017.
 */
(function (ng)
{
    var mod = ng.module("automovilesModule", ['ui.router']);
    mod.constant("automovilContext", "api/automoviles");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Automoviles/';
            $urlRouterProvider.otherwise("/automovilesList");
            $stateProvider
                    .state('automoviles', {
                        url: '/automoviles',
                        abstract: true,
                        resolve:
                                {automoviles: ['$http', 'automovilContext', function ($http, automovilContext) {
                                            //return $http.get(automovilContext);
                                            return $http.get("data/automoviles.json");
                                        }]
                                },
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'automoviles.html',
                                controller: ['$scope', 'automoviles', function ($scope, automoviles)
                                    {
                                        $scope.automovilesRecords = automoviles.data;
                                    }]
                            }
                        }
                    })
                    .state('automovilesList', {
                        url: '/list',
                        parent: 'automoviles',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'automoviles.list.html'
                            }
                        }
                    })
                    .state('automovilesDetail', {
                        url: '/{automovilesId:int}/detail',
                        parent: 'automovilesList',
                        param: {
                            automovilesId: null
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'automoviles.detail.html',
                                controller: ['$scope', '$stateParams', function ($scope, $param)
                                    {
                                        $scope.currentAutomovil = $scope.automovilesRecords[$param.automovilesId - 1]
                                    }]
                            }
                        }

                        // }).state('conductoresDetail', {
                        //     url: '/{conductoresId:int}/detail',
                        //     parent: 'conductores',
                        //     param: {
                        //         conductoresId: null
                        //     },
                        //     views: {
                        //         'listView': {
                        //             templateUrl: basePath + 'conductores.list.html'
                        //         },
                        //         'detailView': {
                        //             templateUrl: basePath + 'conductores.detail.html',
                        //             controller: ['$scope', '$stateParams', function ($scope, $params) {
                        //                 $scope.currentConductores = $scope.conductoresRecords[$params.conductoresId - 1];
                        //             }]
                        //         }
                        //     }
                    }) // .state('conductoresDetail', {
            //     url: '/conductor',
            //     parent: 'conductores',
            //     views: {
            //         'detail': {
            //             templateUrl: basePath + 'conductores.detail.html'
            //         }
            //     }
            // });
        }]);
})(window.angular);
