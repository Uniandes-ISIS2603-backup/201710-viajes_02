(function (ng) {
    var mod = ng.module("cobroModule", ['ui.router']);
    mod.constant("cobroContext", "api/cobros");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Cobro/';
            $urlRouterProvider.otherwise("/cobroList");

            $stateProvider.state('cobros', {
                url: '/cobros',
                abstract: true,
                resolve: {
                    cobros: ['$http', function ($http) {
                            return $http.get('data/cobros.json');
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
            }).state('cobrosDetail',{
                url:'/{cobroId:int}/detail',
                parent:'cobros',
                param: {
                    lugarId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'cobro-detail.html',
                        controller: ['$scope','$stateParams', function($scope, $params) {
                                $scope.currentCobro = $scope.cobrosRecords[$params.cobroId-1 ]
                        }]
                    }
                }
            }).state('cobroUserList', {
                url: '/{usuarioId:int}/cobrosUsuario',
                parent:'cobros',
                param: {
                    usuarioId:null
                },
                views: {
                    'userCobroView': {
                        templateUrl: basePath + 'cobro-user-list.html',
                        controller: ['$scope', '$stateParams', function($scope, $params) {
                            var arreglo = [];
                            var i;
                                                        
                            for(i = 0; i < $scope.cobrosRecords.length; i++) {
                                if($scope.cobrosRecords[i].usuarioRemitente.id === $params.usuarioId) { 
                                    arreglo.push($scope.cobrosRecords[i]);
                                }
                            }                           
                            $scope.userCobros = arreglo
                        }]
                    }
                }
            });
        }]);
})(window.angular);