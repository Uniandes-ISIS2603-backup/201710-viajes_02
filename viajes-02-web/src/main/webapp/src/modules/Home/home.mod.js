(function (ng) {
    var mod = ng.module('homeModule', ['ui.router']);
    mod.constant("lugarContext", "api/lugares");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Home/';
            self = this;
            $urlRouterProvider.otherwise('home');

            $stateProvider.state('home', {
                url: '/home',
                resolve: {
                    lugares: ['$http', 'lugarContext', function ($http, lugarContext) {
                            return $http.get(lugarContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'home.html',
                        controller: ['$scope', 'lugares', function ($scope, lugares) {
                                $scope.lugarRecords = lugares.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);

function datePicker() {
    $('#btnStartDate').datetimepicker({
        format: 'MM/DD/YYYY'
    });
    $('#btnEndDate').datetimepicker({
        useCurrent: false,
        format: 'MM/DD/YYYY'

    });
    $("#btnStartDate").on("dp.change", function (e) {
        $('#btnEndDate').data("DateTimePicker").minDate(e.date);
    });
    $("#btnEndDate").on("dp.change", function (e) {
        $('#btnStartDate').data("DateTimePicker").maxDate(e.date);
    });
}
;

function signIn() {
    var user = $('#signInUser').val();
    var password = $('#signInPassword').val();
    console.log(user + " " + password);
    firebase.auth().signInWithEmailAndPassword(user, password)
            .then(function (firebaseUser) {
                console.log(window.firebaseUser);
                window.firebaseUser = firebaseUser;
                $('#loggedInfo').removeClass("escondido");
                $('#notLoggedInfo').addClass("escondido");
                $('#loggedLabCorreo').text(firebaseUser.email)
            })
            .catch(function (error) {
                console.log('fallo');
            });
}

function signUpConductor() {
    var user = $('#conductorAddressForm').val();
    var password = $('#conductorPasswordInput').val();

    firebase.auth().createUserWithEmailAndPassword(user, password)
            .catch(function (error) {
                console.log('register error', error);
                if (error.code === 'auth/email-already-in-use') {

                }
            });
}

function signUpViajero() {
    var user = $('#viajeroAddressForm').val();
    var password = $('#viajeroPasswordInput').val();

    firebase.auth().createUserWithEmailAndPassword(user, password)
            .catch(function (error) {
                console.log('register error', error);
                if (error.code === 'auth/email-already-in-use') {

                }
            });
}