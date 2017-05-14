/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuarioContext");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Usuario/';
            $urlRouterProvider.otherwise("/usuarioSelect");

            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                resolve: {
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuario.html'
                    }
                }
            }).state('usuarioSelect', {
                url: '/select',
                parent: 'usuarios',
                views: {
                    'selectView': {
                        templateUrl: basePath + 'usuarioSelect.html'
                    }
                }
            });
        }]);
})(window.angular);


