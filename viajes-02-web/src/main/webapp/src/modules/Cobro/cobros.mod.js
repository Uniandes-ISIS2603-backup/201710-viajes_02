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
    
     // POST
     
     /*
    mod.controller('crearLugarController', ['$scope', '$http', 'lugarContext', function ($scope, $http, lugarContext) {
            $scope.crearLugar = function () {
                var data = {
                    "direccion": $("#cobroIdForm").val(),
                    "valor": $("#cobroPriceForm").val(),
                    "cancelado": false
                    
                };

                $http.post(lugarContext, data)
                        .then(function (response) {
                            window.location.reload();
                            validate(null, null, "#error-message-locationForm")
                        },
                                function (response) {
                                    validate(null, null, "#error-message-locationForm", "No se pudo crear lugar intente de nuevo");
                                    console.log("fallo");
                                });
            };
        }]);
*/

// PUT
    
    mod.controller('pagarCobro', ['$scope', '$http', 'cobroContext', function ($scope, $http, cobroContext) {
            $scope.pagarCobro = function (idCobro) {
                var data = {
                    cancelado: true
                };
                
                $http.put(cobroContext + '/' +$scope.currentUsuario.id +"/cobros/" +idCobro, data).then(function (response) {
                    console.log("funciono");
                    window.location.reload();
                }, function (response) {
                    console.log("fallo");
                });
            };
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