angular.module('carrier')
    .controller('CarrierListCtrl', function ($scope, $http) {

        angular.element('#loadingPage').modal('show');

        $scope.carrierList = [];
        $scope.selectedToRemove = {};

        $scope.init = function () {
            $http.get('/admin/carrier/list')
                .then(function (response) {
                    $scope.carrierList = response.data;
                });
            angular.element('#loadingPage').modal('hide');
        };

        $scope.selectToRemove = function (carrier) {
            $scope.selectedToRemove = carrier;
        };

        $scope.removeCarrier = function () {
            $http.delete('/admin/carrier/delete/'+$scope.selectedToRemove.id)
                .then(function () {
                    $scope.init();
                });
        };
    });