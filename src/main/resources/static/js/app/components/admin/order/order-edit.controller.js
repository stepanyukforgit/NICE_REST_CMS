angular.module('order')
    .controller('OrderEditCtrl', function ($scope, $http, $routeParams) {

        angular.element('#loadingPage').modal('show');

        $scope.propList = [];
        $scope.prodList = [];
        $scope.carrList = [];
        $scope.statList = ['НЕ ОБРАБОТАН', 'ПОДТВЕРЖДЁН', 'ОТПРАВЛЕН', 'ЗАКРЫТ'];
        $scope.totalPrice = 0;
        $scope.extraProducts = [];

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $http.get('/admin/order/read/' + $routeParams.orderId)
            .then(function (response) {
                $scope.order = response.data;
                setStatus($scope.order.status);
                $scope.setTotalPrice();
                angular.element('#loadingPage').modal('hide');
            });

        $http.get('/admin/product/list')
            .then(function (response) {
                $scope.prodList = response.data;
            });

        $http.get('/admin/carrier/list')
            .then(function (response) {
                $scope.carrList = response.data;
            });

        $scope.save = function () {
            $scope.validated = true;
            if ($scope.custIdentForm.$valid &&
                $scope.custContForm.$valid &&
                $scope.prodForm.$valid) {

                $scope.saving = true;
                $scope.saved = false;
                $scope.notSaved = false;

                rebuildOrder();

                $http.put('/admin/order/save', $scope.order)
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
            }
        };

        $scope.getProductProposals = function (extraProduct, idx) {
            $scope.extraProducts[idx] = angular.copy(extraProduct);

            if (extraProduct) {
                $http.get('/admin/product_proposal/read/product_id/' + $scope.extraProducts[idx].id)
                    .then(function (response) {
                        $scope.propList[idx] = response.data;

                        // todo make check if availability false - disable element.
                        // todo on back make select product with prodProp only and remove this if
                        if ($scope.propList[idx].length > 0) {
                            $scope.extraProducts[idx].proposal = $scope.propList[idx][0];
                            $scope.extraProducts[idx].quantity = 1;
                        }
                        $scope.setTotalPrice();
                    });
            } else {
                $scope.propList[idx] = [];
                $scope.setTotalPrice();
            }
        };

        $scope.setTotalPrice = function () {

            var orderProdPrice = $scope.order.orderedProducts.reduce(
                function (sum, curr) {
                    if (curr) {
                        sum += curr.quantity * (curr.price - (curr.price * (curr.sale / 100)));
                    }
                    return Math.round(sum);
                }
                , 0);

            var extraProdPrice = $scope.extraProducts.reduce(
                function (sum, curr) {
                    if (curr && curr.proposal) {
                        sum += curr.quantity * (curr.proposal.price - (curr.proposal.price * (curr.proposal.sale / 100)));
                    }
                    return sum;
                }
                , 0);

            $scope.totalPrice = orderProdPrice + extraProdPrice;
        };

        $scope.changeStatus = function (status) {
            switch (status) {
                case'НЕ ОБРАБОТАН' : {
                    $scope.order.status = 'UNHANDLED';
                    break;
                }
                case'ПОДТВЕРЖДЁН' : {
                    $scope.order.status = 'CONFIRMED';
                    break;
                }
                case'ОТПРАВЛЕН' : {
                    $scope.order.status = 'SUBMITTED';
                    break;
                }
                case'ЗАКРЫТ' : {
                    $scope.order.status = 'CLOSED';
                    break;
                }
            }
        };

        $scope.addExtraProduct = function () {
            $scope.extraProducts.push({});
        };

        $scope.removeExtraProduct = function (extraIdx) {
            if ($scope.extraProducts.length > 1 || $scope.order.orderedProducts.length >= 1) {
                $scope.extraProducts.splice(extraIdx, 1);
                $scope.propList.splice(extraIdx, 1);
            }
            $scope.setTotalPrice();
        };

        $scope.removeOrderedProduct = function (ordProdIdx) {
            if ($scope.order.orderedProducts.length > 1 || $scope.extraProducts.length >= 1) {
                $scope.order.orderedProducts.splice(ordProdIdx, 1);
            }
            $scope.setTotalPrice();
        };

        function setStatus(status) {
            switch (status) {
                case'UNHANDLED' : {
                    $scope.status = 'НЕ ОБРАБОТАН';
                    break;
                }
                case'CONFIRMED' : {
                    $scope.status = 'ПОДТВЕРЖДЁН';
                    break;
                }
                case'SUBMITTED' : {
                    $scope.status = 'ОТПРАВЛЕН';
                    break;
                }
                case'CLOSED' : {
                    $scope.status = 'ЗАКРЫТ';
                    break;
                }
            }
        };

        function rebuildOrder() {

            for (var i = 0; i < $scope.extraProducts.length; i++) {

                var newOrdProdIdx = $scope.order.orderedProducts.length;

                $scope.order.orderedProducts[newOrdProdIdx] = {};
                $scope.order.orderedProducts[newOrdProdIdx].name = $scope.extraProducts[i].name;
                $scope.order.orderedProducts[newOrdProdIdx].proposalName = $scope.extraProducts[i].proposal.name;
                $scope.order.orderedProducts[newOrdProdIdx].price = $scope.extraProducts[i].proposal.price;
                $scope.order.orderedProducts[newOrdProdIdx].sale = $scope.extraProducts[i].proposal.sale;
                $scope.order.orderedProducts[newOrdProdIdx].proposalName = $scope.extraProducts[i].proposal.name;
                $scope.order.orderedProducts[newOrdProdIdx].vendorCode = $scope.extraProducts[i].proposal.vendorCode;
                $scope.order.orderedProducts[newOrdProdIdx].manufacturerName = $scope.extraProducts[i].manufacturerName;
                $scope.order.orderedProducts[newOrdProdIdx].quantity = $scope.extraProducts[i].quantity;
                $scope.order.orderedProducts[newOrdProdIdx].attributes = {};
                $scope.order.orderedProducts[newOrdProdIdx].attributes.ru = stringAttrs($scope.extraProducts[i].proposal.attributes, 'ru');
                $scope.order.orderedProducts[newOrdProdIdx].attributes.ua = stringAttrs($scope.extraProducts[i].proposal.attributes, 'ua');
            }

            $scope.extraProducts = [];

            function stringAttrs(arr, lang) {
                return arr.reduce(function (attrs, attr) {
                    return attrs += attr.name[lang] + ' ' + attr.value[lang] + '; ';
                }, '');
            };
        };
    });