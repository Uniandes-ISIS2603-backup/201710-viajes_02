(function (ng) {
    var mod = ng.module('homeModule', ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Home/';
            self = this;
            $urlRouterProvider.otherwise('home');

            $stateProvider.state('home', {
                url: '/home',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'home.html'
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