angular.module('order')
    .controller('OrderListCtrl', function ($scope, $http) {

        angular.element('#loadingPage').modal('show');

        $scope.orderList = [];
        $scope.selectedToRemove = {};

        $scope.init = function () {
            $http.get('/admin/order/list')
                .then(function (response) {
                    $scope.orderList = response.data;
                    $scope.orderList.forEach(function (order) { order.date = new Date(order.date) });
                });
            angular.element('#loadingPage').modal('hide');
        };

        $scope.selectToRemove = function (order) {
            $scope.selectedToRemove = order;
        };

        $scope.removeOrder = function () {
            $http.delete('/admin/order/delete/'+$scope.selectedToRemove.id)
                .then(function () {
                    $scope.init();
                });
        };

        $scope.formatDate = function (date) {
            var dd = date.getDate();
            if (dd < 10) dd = '0' + dd;
            var mm = date.getMonth() + 1;
            if (mm < 10) mm = '0' + mm;
            var yy = date.getFullYear() % 100;
            if (yy < 10) yy = '0' + yy;

            return dd + '.' + mm + '.' + yy;
        };

        $scope.setStatus = function (status) {
            switch (status) {
                case'UNHANDLED' : {
                    return 'НЕ ОБРАБОТАН';
                }
                case'CONFIRMED' : {
                    return 'ПОДТВЕРЖДЁН';
                }
                case'SUBMITTED' : {
                    return 'ОТПРАВЛЕН';
                }
                case'CLOSED' : {
                    return 'ЗАКРЫТ';
                }
            }
        };
    });