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
                    currentUsuario: ['$http', 'cobroContext', '$stateParams', function ($http, cobroContext, $params) {
                            return $http.get(cobroContext + '/' + $params.usuarioId);
                        }],
                    cobros: ['$http', 'cobroContext', '$stateParams', function ($http, cobroContext, $params) {
                            return $http.get(cobroContext + '/' + $params.usuarioId + '/cobros');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'cobro.html',
                        controller: ['$scope', 'cobros', 'currentUsuario', function ($scope, cobros, currentUsuario) {
                                $scope.currentUsuario = currentUsuario.data;
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
            });
        }]);
})(window.angular);

 function filtrarContenido(algo) {
      var $target = $(algo).data('target');

     if ($target !== 'all') {
        $('.table tr').css('display', 'none');
        $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
      } else {
        $('.table tr').css('display', 'none').fadeIn('slow');
      }
}