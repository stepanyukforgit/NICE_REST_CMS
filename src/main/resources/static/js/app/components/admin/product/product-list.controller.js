angular.module('product')
    .controller('ProductListCtrl', function ($scope, $http) {

        angular.element('#loadingPage').modal('show');

        $scope.product = [];
        $scope.selectedToRemove = {};

        $scope.init = function () {
            $http.get('/admin/product/list')
                .then(function (response) {
                    $scope.productList = response.data;
                });
            angular.element('#loadingPage').modal('hide');
        };

        $scope.selectToRemove = function (product) {
            $scope.selectedToRemove = product;
        };

        $scope.removeProduct = function () {
            $http.delete('/admin/product/delete/' + $scope.selectedToRemove.id)
                .then(function () {
                    $scope.init();
                });
        };
    });