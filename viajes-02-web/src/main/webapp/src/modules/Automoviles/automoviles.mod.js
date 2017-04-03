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
                { automoviles: ['$http', function( $http ) {
            return $http.get('data/automoviles.json');

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
            views: {
                'detailView': {
                    templateUrl: basePath + 'automoviles.detail.html',
                    controller: ['$scope', '$stateParams', function ($scope, $stateParams) {
                        $scope.currentAutomoviles = $scope.automovilesRecords[$stateParams.automovilesId-1];
                    }]
                }

            }

        });
    }]);
})(window.angular);
