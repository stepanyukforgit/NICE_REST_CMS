angular.module('order')
    .controller('OrderCreateCtrl', function ($scope, $http) {
        $scope.order = {
            orderedProducts: [{}],
            status: 'UNHANDLED',
            isPayed: false
        };

        $scope.propList = [];
        $scope.prodList = [];
        $scope.carrList = [];
        $scope.statList = ['НЕ ОБРАБОТАН', 'ПОДТВЕРЖДЁН', 'ОТПРАВЛЕН', 'ЗАКРЫТ'];

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

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

                var orderRebuilded = rebuildOrder();

                $http.post('/admin/order/save', orderRebuilded)
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

        $scope.getProductProposals = function (orderedProduct, idx) {
            $scope.order.orderedProducts[idx] = angular.copy(orderedProduct);

            if (orderedProduct) {
                $http.get('/admin/product_proposal/read/product_id/' + $scope.order.orderedProducts[idx].id)
                    .then(function (response) {
                        $scope.propList[idx] = response.data;

                        // todo make check if availability false - disable element.
                        // todo on back make select product with prodProp only and remove this if
                        if ($scope.propList[idx].length > 0) {
                            $scope.order.orderedProducts[idx].proposal = $scope.propList[idx][0];
                            $scope.order.orderedProducts[idx].quantity = 1;
                        }
                    });
            } else {
                $scope.propList[idx] = [];
            }
        };

        $scope.setStatus = function (status) {
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

        $scope.addOrderedProduct = function () {
            $scope.order.orderedProducts.push({});
        };

        $scope.removeOrderedProduct = function (ordProdIdx) {
            if ($scope.order.orderedProducts.length > 1) {
                $scope.order.orderedProducts.splice(ordProdIdx, 1);
                $scope.propList.splice(ordProdIdx, 1);
            }
        };

        function rebuildOrder(){

            var o = angular.copy($scope.order);

            for (var i = 0; i < o.orderedProducts.length; i++){
                o.orderedProducts[i].proposalName = o.orderedProducts[i].proposal.name;
                o.orderedProducts[i].vendorCode = o.orderedProducts[i].proposal.vendorCode;
                o.orderedProducts[i].price = o.orderedProducts[i].proposal.price;
                o.orderedProducts[i].sale = o.orderedProducts[i].proposal.sale;
                o.orderedProducts[i].attributes = {};
                o.orderedProducts[i].attributes.ru = stringAttrs(o.orderedProducts[i].proposal.attributes, 'ru');
                o.orderedProducts[i].attributes.ua = stringAttrs(o.orderedProducts[i].proposal.attributes, 'ua');

                delete o.orderedProducts[i].proposal;
                delete o.orderedProducts[i].id;
                delete o.orderedProducts[i].photo;
            }

            return o;

            function stringAttrs(arr, lang){
                return arr.reduce(function(attrs, attr){
                    return attrs += attr.name[lang] + ' ' + attr.value[lang] + '; ';
                }, '');
            };
        };
    });