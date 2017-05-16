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
                                self.scope = $scope;

                                addAllMarkers($scope.lugarRecords, $scope.map);
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
                            console.log("hola")
                            return $http.get(lugarContext + '/' + $params.lugarId);
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugar-list.html',
                        controller: ['$scope', 'currentLugar', function ($scope, currentLugar) {
                                $scope.currentLugar = currentLugar.data;
                                var uluru = {lat: $scope.currentLugar.lat, lng: $scope.currentLugar.lon};

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
                                self.scope = $scope;

                                addAllMarkers($scope.lugarRecords, $scope.map);
                            }]
                    }

                }
            });
        }]);

    // POST
    mod.controller('crearLugarController', ['$scope', '$http', 'lugarContext', function ($scope, $http, lugarContext) {
            $scope.crearLugar = function () {
                var data = {
                "direccion": $("#placeAddressForm").val(),
                "lat": $("#placeLatForm").val(),
                "lon": $("#placeLonForm").val(),
                "lugar": $("#placeNameForm").val(),
                "rutaImagen": $("#placeImageURLForm").val()
            };
            
                $http.post(lugarContext, data)
                        .then(function (response) {
                            window.location.reload();
                        },
                                function (response) {
                                    console.log("fallo");
                                });
            };
        }]);
})(window.angular);

// Map Helpers
function goTo() {
    var searchName = $('#locationName').val();

    var found = false;
    for (var i = 0; i < window.scope.lugarRecords.length && !found; i++) {
        var name = window.scope.lugarRecords[i].lugar.toUpperCase()
        if (name == searchName.toUpperCase()) {
            centerIn(window.scope.lugarRecords[i].lat, window.scope.lugarRecords[i].lon);
            found = true;
            validate(null, '#locationName', '#error-message-location');
        }
    }

    if (!found) {
        validate("add", '#locationName', '#error-message-location', "No existe el lugar indicado");
    }
}

function centerIn(lat, lng) {
    var location = new google.maps.LatLng(lat, lng);
    window.mapLiteral.setCenter(location);
}

function validate(action, elementId, elementErrorId, errorMessage) {
    if (action !== null) {
        $(elementId).addClass("error");
        $(elementErrorId).show();
        console.log($(elementErrorId))
        if (errorMessage !== undefined) {
            $(elementErrorId).text(errorMessage);
        }

    } else {
        $(elementId).removeClass("error");
        $(elementId).val("");
        $(elementErrorId).hide();
    }
}

function addAllMarkers(records, map) {
    var allMarkers = [];
    for (var i = 0; i < records.length; i++) {
        var location = {lat: records[i].lat, lng: records[i].lon};

        var icon = {
            url: 'css/marker.png',
            scaledSize: new google.maps.Size(50, 85),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        };

        var marker = new google.maps.Marker({
            position: location,
            map: map,
            placeName: records[i].lugar,
            placeAddress: records[i].direccion,
            placeImage: records[i].rutaImagen,
            icon: icon
        });

        google.maps.event.addListener(marker, 'click', function () {
            $('#basicModal').modal('show');
            $('#basicModal-lugarName').text(this.placeName);
            $('#basicModal-lugarAddress').text(this.placeAddress);
            $('#basicModal-lugarImage').attr("src", this.placeImage);
        });

        allMarkers.push(marker);
    }
    map.markers = allMarkers;
}
