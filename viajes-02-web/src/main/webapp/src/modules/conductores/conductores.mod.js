(function (ng) {
    var mod = ng.module("conductorModule", ['ui.router']);
    mod.constant("conductoresContext", "api/Conductores");
    mod.constant("automovilContext", "api/automoviles");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/conductores/';
            $urlRouterProvider.otherwise("/conductoresList");
            self = this;
            $stateProvider.state('conductores', {
                url: '/conductores',
                abstract: true,
                resolve: {
                    conductores: ['$http', 'conductoresContext', function ($http, conductoresContext) {
                            return $http.get(conductoresContext);
                        }]
                    
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'conductores.html',
                        controller: ['$scope', 'conductores', function ($scope, conductores) {
                                $scope.conductoresRecords = conductores.data;
                            }]
                    }
                }
            })
                    .state('conductoresList', {
                        url: '/list',
                        parent: 'conductores',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'conductores.list.html'
                            }
                        }
                    })
                    .state('conductorDetail', {
                        url: '/{conductorId:int}/detail',
                        parent: 'conductores',
                        param: {
                            conductorId: null
                        },
                        resolve: {
                            currentConductor: ['$http', 'conductoresContext', '$stateParams', function ($http, conductoresContext, $params) {
                                    return $http.get(conductoresContext + '/' + $params.conductorId);
                                }],
                            currentReservas:  ['$http', '$stateParams', function ($http, $params) {
                                    return $http.get('api/usuarios/reviews' + '/' + $params.conductorId);
                                }]
                        },
                        views: {
                            'detailView': {
                                templateUrl: basePath + 'conductores.detail.html',
                                controller: ['$scope', 'currentConductor','currentReservas', function ($scope, currentConductor, currentReservas) {
                                        $scope.currentConductor = currentConductor.data;
                                        $scope.currentConductor.reviews = currentReservas.data;
                                        
                                        
                                        
                                        
                                    }]
                            },
                            'listView': {
                                templateUrl: 'src/modules/viajes/' + 'viajes.list.html',
                                controller: ['$scope', 'currentConductor', function ($scope, currentConductor) {
                                        $scope.viajesRecords = currentConductor.data.viajes;
                                    }]
                            }
                        }
                    })
                    .state('conductoresDetailReview', {
                        url: '/reviews',
                        parent: 'conductorDetail',

                        views: {
                            'detail': {
                                templateUrl: basePath + 'conductores.detail.reviews.html',
                                controller: ['$scope', '$http', 'currentConductor', '$stateParams', '$state',
                                    function ($scope, $http, currentConductor, $stateParams, $state) {
                                        $scope.currentConductor = currentConductor.data;
                                            // console.log(currentConductor.data.id)
                                        var rev= $http.get('api/usuarios/reviews/'+currentConductor.data.id)
                                        console.log(rev)
                                        $scope.eliminarev = function (idrev) {

                                            $http.delete('api/usuarios/reviews/' +idrev)


                                                .then(function () {
                                                    $state.go('conductoresDetailAutomoviles', {conductorId: $scope.currentConductor.id}, {reload: true})
                                                });
                                        }
                                    }]
                            }
                        }
                    })
                    .state('conductoresDetailAutomoviles', {
                        url: '/automoviles',
                        parent: 'conductorDetail',
                        views: {
                            'detail': {
                                templateUrl: basePath + 'conductores.detail.automoviles.html'
                            }
                        }
                    })
                    .state('conductoresAdd', {
                        url: '/add',
                        parent: 'conductores',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'conductores.anadir.html',
                                controller: ['$scope', 'conductoresContext', '$http', '$state', function ($scope, conductoresContext, $http, $state) {
                                        $scope.conductor = {
                                            id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                                            nombre: '' /*Tipo String*/,
                                            correo: '',
                                            genero: '',
                                            telMovil: '',
                                            edad: '',
                                            imagen: ''

                                        };
                                        $scope.submit = function () {
                                            currentConductor = $scope.conductor;
                                            return $http.post(conductoresContext, currentConductor)
                                                    .then(function () {
                                                        // $http.post es una promesa
                                                        // cuando termine bien, cambie de estado
                                                        $state.go('conductoresList', {}, {reload: true});
                                                        signUpConductor();
                                                    });
                                        };
                                    }]
                            }
                        }
                    }).state('conductoresEdit', {
                url: '/{conductorId:int}/edit',
                parent: 'conductores',
                param: {
                    conductorId: null
                },
                resolve: {
                    currentConductor: ['$http', 'conductoresContext', '$stateParams', function ($http, conductoresContext, $params) {
                            return $http.get(conductoresContext + '/' + $params.conductorId);
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'conductores.editar.html',
                        controller: ['$scope', 'conductoresContext', '$http', '$state', 'currentConductor', function ($scope, conductoresContext, $http, $state, currentConductor) {
                                $scope.conductor = currentConductor.data;
                                $scope.submit = function () {
                                    currentConductor = $scope.conductor;

                                    return $http.put(conductoresContext + '/' + currentConductor.id, currentConductor)
                                            .then(function () {
                                                // $http.post es una promesa
                                                // cuando termine bien, cambie de estado
                                                $state.go('conductoresList', {}, {reload: true});
                                            });
                                };
                            }]
                    }
                }
            })
                    .state('reviewsFormulario', {
                        url: '/addReview',
                        parent: 'conductorDetail',
                        views: {
                            'detail': {
                                templateUrl: basePath + 'reviews.formulario.html',
                                controller: ['$scope', '$http', 'conductoresContext', '$state',
                                    function ($scope, $http, conductoresContext, $state) {
                                        var valor = 0;
                                        $scope.agregarRev = function () {
                                            console.log('pruebaaaa'+document.getElementById('radio1').value);
                                            if (document.getElementById('radio1').value)
                                                valor = document.getElementById('radio1').value;
                                            // else if (document.getElementById('radio2').value)
                                            //     valor = 2;
                                            // else if (document.getElementById('radio3').value)
                                            //     valor = 3;
                                            // // else if (document.getElementById('radio4').value)
                                            //     valor = 4;
                                            // else
                                            //     valor = 5;
                                            var comment = document.getElementById('comment').value;
                                            var idCalificado = $scope.currentConductor.id;
                                            var idCalificador = parseInt(document.getElementById('idCalificador').value);
                                            console.log(valor);
                                            console.log(comment);
                                            console.log(idCalificado);
                                            console.log(idCalificador);
                                            var review = {
                                                calificacion: valor,
                                                coment: comment,
                                                idCalificado: idCalificado,
                                                idCalificador: idCalificador
                                            };
                                            console.log(review);

                                            $http.post('api/usuarios/reviews', review)
                                                    .then(function () {
                                                        // $http.post es una promesa
                                                        // cuando termine bien, cambie de estado
                                                        $state.go('conductorDetail', {conductorId: $scope.currentConductor.id}, {reload: true});
                                                    });
                                        }
                                    }]
                            }
                        }
                    })
                    .state('automovilesFormulario', {
                        url: '/addAuto',
                        parent: 'conductorDetail',
                        views: {
                            'detail': {
                                templateUrl: basePath + 'automoviles.formulario.html',
                                controller: ['$scope', '$http', 'conductoresContext', '$state',
                                    function ($scope, $http, conductoresContext, $state) {

                                        $scope.agregar = function () {
                                            var placa = document.getElementById('placa').value;
                                            var color = document.getElementById('color').value;
                                            var marca = document.getElementById('marca').value;
                                            var modelo = document.getElementById('modelo').value;
                                            var aseg = document.getElementById('aseguradora').value;
                                            var numseg = parseInt(document.getElementById('numseguro').value);
                                            var cantasientos = document.getElementById('cantasientos').value;
                                            var carrito = {
                                                marca: marca,
                                                modelo: modelo,
                                                cantAsientos: cantasientos,
                                                color: color,
                                                compSeguros: aseg,
                                                numSeguro: numseg,
                                                placa: placa,
                                                conductorDTO: {
                                                    id: $scope.currentConductor.id
                                                }
                                            };

                                            carrito = $http.post('api/automoviles', carrito)


                                                    .then(function () {
                                                        // $http.post es una promesa
                                                        // cuando termine bien, cambie de estado
                                                        $state.go('conductorDetail', {conductorId: $scope.currentConductor.id}, {reload: true});
                                                    });
                                        };
                                    }]
                            }
                        }
                    })
                    .state('automovilesDetail', {
                        url: '/{autoId:int}/AutoDetail',
                        parent: 'conductoresDetailAutomoviles',
                        param: {
                            autoId: null
                        },
                        resolve: {
                            currentAutomovil: ['$http', 'automovilContext', '$stateParams',
                                function ($http, automovilContext, $params) {
                                    return $http.get(automovilContext + '/' + $params.autoId);
                                }]
                        },
                        views: {
                            'childrenView': {
                                templateUrl: basePath + 'automoviles.list.html',
                                controller: ['$scope', '$http', 'currentAutomovil', '$stateParams', '$state',
                                    function ($scope, $http, currentAutomovil, $stateParams, $state) {
                                        $scope.currentAutomovil = currentAutomovil.data;

                                        $scope.eliminarAutomovil = function () {
                                            $http.delete('api/automoviles/' + $stateParams.autoId)
                                                    .then(function () {
                                                        $state.go('conductoresDetailAutomoviles', {conductorId: $scope.currentConductor.id}, {reload: true})
                                                    });
                                        }
                                    }]
                            }
                        }
                    })
                    // .state('automovilesActualizar', {
                    //     url: '/AutoDetailF',
                    //     parent: 'automovilesDetail',
                    //     views: {
                    //         'automovilesDetail@childrenView': {
                    //             templateUrl: basePath + 'automoviles.actualizar.html',
                    //             controller: ['$scope', '$http', '$stateParams', '$state',
                    //                 function ($scope, $http, $stateParams, $state) {
                    //
                    //                     document.getElementById('pplaca').value = $scope.currentAutomovil.placa;
                    //                     // document.getElementById('mmarca').value = $scope.currentAutomovil.marca;
                    //                     // document.getElementById('mmodelo').value = $scope.currentAutomovil.modelo;
                    //
                    //
                    //                 }]
                    //             //
                    //             //        document.getElementById('placa').value = $scope.currentAutomovil.id;
                    //             //          document.getElementById('marca').value=  $scope.currentAutomovil.marca;
                    //             //         document.getElementById('modelo').value= $scope.currentAutomovil.modelo;
                    //             //
                    //             //         $scope.actualizar = function () {
                    //             //           var   placa = document.getElementById('placa').value;
                    //             //           var   color = document.getElementById('color').value;
                    //             //           var   marca = document.getElementById('marca').value;
                    //             //           var   modelo = document.getElementById('modelo').value;
                    //             //           var   aseg = document.getElementById('aseguradora').value;
                    //             //           var   numseg = document.getElementById('numseguro').value;
                    //             //           var   cantasientos = document.getElementById('cantasientos').value;
                    //             //             var  carrito = {
                    //             //                 marca: marca,
                    //             //                 modelo: modelo,
                    //             //                 cantAsientos: cantasientos,
                    //             //                 color: color,
                    //             //                 compSeguros: aseg,
                    //             //                 numSeguro: numseg,
                    //             //                 placa: placa,
                    //             //                 conductorDTO: {
                    //             //                     id: $scope.currentConductor.id
                    //             //                 }
                    //             //             };
                    //             //
                    //             //             carrito = $http.put('api/automoviles/' + $scope.currentAutomovil.id, carrito)
                    //             //                 .then(function () {
                    //             //                     $state.go('conductoresDetailAutomoviles', {conductorId: $scope.currentConductor.id}, {reload: true})
                    //             //                 });
                    //             //         }
                    //             //     }]
                    //         }
                    //     }
                    // })
                    ;
        }])
})
        (window.angular);


