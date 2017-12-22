angular.module('manufacturer')
    .controller('ManufacturerListCtrl', function ($scope, $http) {

        angular.element('#loadingPage').modal('show');

        $scope.manufacturerList = [];
        $scope.selectedToRemove = {};

        $scope.init = function () {
            $http.get('/admin/manufacturer/list')
                .then(function (response) {
                    $scope.manufacturerList = response.data;
                });
            angular.element('#loadingPage').modal('hide');
        };

        $scope.selectToRemove = function (manufacturer) {
            $scope.selectedToRemove = manufacturer;
        };

        $scope.removeManufacturer = function () {
            $http.delete('/admin/manufacturer/delete/'+$scope.selectedToRemove.id)
                .then(function () {
                    $scope.init();
                });
        };
    });