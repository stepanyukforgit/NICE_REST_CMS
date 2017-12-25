angular.module('product-category')
    .controller('ProductCategoryListCtrl', function ($scope, $http) {

        angular.element('#loadingPage').modal('show');

        $scope.productCategory = [];
        $scope.selectedToRemove = {};

        $scope.init = function () {
            $http.get('/admin/product_category/list')
                .then(function (response) {
                    $scope.productCategoryList = response.data;
                });
            angular.element('#loadingPage').modal('hide');
        };

        $scope.selectToRemove = function (productCategory) {
            $scope.selectedToRemove = productCategory;
        };

        $scope.removeProductCategory = function () {
            $http.delete('/admin/product_category/delete/'+$scope.selectedToRemove.id)
                .then(function () {
                    $scope.init();
                });
        };
    });