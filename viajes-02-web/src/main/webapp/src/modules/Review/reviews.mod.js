(function (ng) {
    var mod = ng.module("reviewModule", ['usuarioModule', 'ui.router']);

    mod.constant("usuarioContext", "api/usuario");

    mod.constant("reviewsContext", "reviews");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reviews/';
            $urlRouterProvider.otherwise("/reviewsList");

            $stateProvider.state('reviews', {
                url: '/reviews',
                abstract: true,
                parent: 'usuarioDetail',
                resolve: {
                    reviews: ['$http', 'usuarioContext', 'reviewsContext', '$stateParams', function ($http, usuarioContext, reviewsContext, $params) {
                            return $http.get(usuarioContext + '/' + $params.usuarioId + '/' + reviewsContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'reviews.html'
                    }
                },
            }).state('reviewsList', {
                url: '/list',
                parent: 'reviews',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reviews.list.html',
                        controller: ['$scope', 'reviews', function ($scope, reviews) {
                                $scope.reviewsRecords = reviews.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);