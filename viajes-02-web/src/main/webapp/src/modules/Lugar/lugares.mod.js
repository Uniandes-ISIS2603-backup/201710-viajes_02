(function (ng) {
    var mod = ng.module("lugarModule", ['ui.router']);
    mod.constant("lugarContext", "api/lugares");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Lugar/';
            $urlRouterProvider.otherwise("/lugarList");
            self = this;
            $stateProvider.state('lugares', {
                url: '/lugares',
                abstract: true,
                resolve: {
                    lugares: ['$http', 'lugarContext', function ($http, lugarContext) {
                            return $http.get(lugarContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'lugar.html',
                        controller: ['$scope', 'lugares', function ($scope, lugares) {
                                $scope.lugarRecords = lugares.data;
                            }]
                    }
                }
            }).state('lugarList', {
                url: '/list',
                parent: 'lugares',
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugar-list.html',
                        controller: ['$scope', function ($scope) {
                                var uluru = {lat: 4.6535241, lng: -74.1105382};

                                $scope.mapOptions = {
                                    zoom: 17,
                                    center: uluru,
                                    draggable: false,
                                    zoomControl: false,
                                    scrollwheel: false,
                                    disableDoubleClickZoom: true
                                };

                                $scope.map = new google.maps.Map(document.getElementById('map'), $scope.mapOptions);
                                self.mapLiteral = $scope.map;
                                
                                var marker = new google.maps.Marker({
                                    position: uluru,
                                    map: $scope.map
                                });
                            }
                        ]
                    }
                },
            }).state('lugarDetail', {
                url: '/{lugarId:int}/detail',
                parent: 'lugares',
                param: {
                    lugarId: null
                },
                resolve: {
                    currentLugar: ['$http', 'lugarContext', '$stateParams', function ($http, lugarContext, $params) {
                            return $http.get(lugarContext + '/' + $params.lugarId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'lugar-detail.html',
                        controller: ['$scope', 'currentLugar', function ($scope, currentLugar) {
                                $scope.currentLugar = currentLugar.data;
                            }]
                    }

                }
            });
        }]);
})(window.angular);

function marker() {
    var buenas = new google.maps.LatLng(4.6476286,-74.1038169);
    console.log(window.mapLiteral.setCenter(buenas));
}