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
};