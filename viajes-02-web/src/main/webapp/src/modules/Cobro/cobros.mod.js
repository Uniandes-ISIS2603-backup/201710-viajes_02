(function (ng) {
    var mod = ng.module("cobroModule", ['ui.router']);
    mod.constant("cobroContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Cobro/';
            $urlRouterProvider.otherwise("/cobroList");

            $stateProvider.state('cobros', {
                url: '/usuarios/{usuarioId:int}/cobros',
                abstract: true,
                param: {
                    usuarioId: null
                },
                resolve: {
                    cobros: ['$http', 'cobroContext', '$stateParams', function ($http, cobroContext, $params) {
                            return $http.get(cobroContext + '/' + $params.usuarioId + '/cobros');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'cobro.html',
                        controller: ['$scope', 'cobros', function ($scope, cobros) {
                                $scope.cobrosRecords = cobros.data;
                            }]
                    }
                }
            }).state('cobroList', {
                url: '/list',
                parent: 'cobros',
                views: {
                    'listView': {
                        templateUrl: basePath + 'cobro-list.html'
                    }
                }
            }).state('cobrosDetail', {
                url: '/{cobroId:int}',
                parent: 'cobros',
                param: {
                    cobroId: null
                },
                resolve: {
                    currentCobro: ['$http', 'cobroContext', '$stateParams', function ($http, cobroContext, $params) {
                            return $http.get(cobroContext + '/' + $params.usuarioId + '/cobros/' + $params.cobroId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'cobro-detail.html',
                        controller: ['$scope', 'currentCobro', function ($scope, currentCobro) {
                                $scope.currentCobro = currentCobro.data
                                console.log($scope.currentCobro)
                            }]
                    }
                }
            });
        }]);
})(window.angular);