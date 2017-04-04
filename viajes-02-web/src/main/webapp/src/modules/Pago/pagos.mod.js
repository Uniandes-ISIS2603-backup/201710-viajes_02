/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);
    mod.constant("pagosContext", "api/pagos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/Pago/';
            
            $urlRouterProvider.otherwise("/pagosList");

            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                resolve: {
                    pagos: ['$http', function ($http) {
                            return $http.get('data/pagos.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.html',
                        controller: ['$scope', 'pagos', function ($scope, pagos) {
                                $scope.pagosRecords = pagos.data;
                            }]
                    }
                }
            }).state('pagosList', {
                url: '/list',
                parent: 'pagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pago-list.html'
                    }
                }
            }).state('pagosDetail', {
                url: '/{pagoId:int}/detail',
                parent: 'pagos',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'pagos.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentPago = $scope.pagosRecords[$params.pagoId - 1 ];
                            }]
                    }
                }
            });
        }]);
})(window.angular);