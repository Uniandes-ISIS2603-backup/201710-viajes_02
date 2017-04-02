/**
 * Created by Danny on 1/04/2017.
 */
(function (ng)
{
    var mod = ng.module("automovilesModule",['ui.router']);
    mod.constant("automovilContext","api/automoviles");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/automoviles/';
        $urlRouterProvider.otherwise("/automovilesList");
        $stateProvider.state('automoviles',{
            url: '/automoviles',
            abstract: true,
            resolve:
                { automoviles: ['$http', 'automovilesContext', function( $http, automovilesContext) {
            return $http.get(automovilesContext);

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
                    return $http.get(automovilesContext+'/'+$params.automovilesId);
                }]
            },
            views: {
                'detailView': {
                    templateUrl: basePath + 'automoviles.detail.html',
                    controller: ['$scope', '$stateParams', function ($scope, $stateParams) {
                        $scope.currentAutomoviles = $scope.automovilesRecords[$params.automovilesId-1];
                    }]
                }

            }

        });
    }]);
})(window.angular);
