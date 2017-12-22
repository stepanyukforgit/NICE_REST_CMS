angular.module('shopinfo')
    .controller('ShopinfoCtrl', function ($scope, $http) {

        angular.element('#loadingPage').modal('show');

        $scope.shopinfo = {};

        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $http.get('/admin/shopinfo/read')
            .then(function (response) {
                $scope.shopinfo = response.data;
                angular.element('#loadingPage').modal('hide');
            });

        $scope.saveShopinfo = function () {

            $scope.saving = true;
            $scope.saved = false;
            $scope.notSaved = false;

            $http.post('/admin/shopinfo/save', $scope.shopinfo)
                .then(
                    function success() {
                        $scope.saving = false;
                        $scope.saved = true;
                        $scope.notSaved = false;
                    },
                    function error() {
                        $scope.saving = false;
                        $scope.saved = false;
                        $scope.notSaved = true;
                    });
        };

        $scope.removeShopinfo = function () {
            $http.delete('/admin/shopinfo/delete')
                .then(function () {
                        $scope.shopinfo = {};
                    }
                );
        }
    });