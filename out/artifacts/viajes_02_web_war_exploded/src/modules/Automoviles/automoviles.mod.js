/**
 * Created by Danny on 1/04/2017.
 */
(function (ng)
{
    var mod = ng.module("automovilesModule",['ui.router']);
    mod.constant("automovilContext","api/automoviles");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/Automoviles/';
        $urlRouterProvider.otherwise("/automovilesList");
        $stateProvider.state('automoviles',{
            url: '/automoviles',
            abstract: true,
            resolve:
                { automoviles: ['$http', 'automovilContext', function( $http, automovilContext ) {
                 return $http.get(automovilContext);

        }]
    },
          views:{
                'mainView':{
                    templateUrl: basePath + 'automoviles.html',
                    controller: ['$scope', 'automoviles', function ($scope, automoviles)
                    {
                        $scope.automovilesRecords = automoviles.data;
                    }]
                }
          }
            }).state('automovilesList', {
            url: '/list',
            parent: 'automoviles',
            views: {
                'listView': {
                    templateUrl: basePath + 'automoviles.list.html'
                }
            }
        }).state('automovilesDetail', {
            url: '/{automovilesId:int}/detail',
            parent: 'automoviles',
            param: {
                automovilesId: null
            },
            resolve:{
                currentAutomoviles:['$http', 'automovilesContext', '$stateParams', function ($http, automovilesContext, $params) {
                    return $http.get(automvovilesContext+'/'+$params.automovilesId);
                }]
            },
            views: {
                'detailView': {
                    templateUrl: basePath + 'automoviles.detail.html',
                    controller: ['$scope', '$stateParams', function ($scope, $stateParams) {
                        $scope.currentAutomoviles = $scope.automovilesRecords[$stateParams.automovilesId-1];
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
