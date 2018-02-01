var module = angular.module('product', []);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_product_create',
            {templateUrl: 'admin/a_product_create.html'})
        .when('/a_product_edit/:productId',
            {templateUrl: 'admin/a_product_edit.html'})
        .when('/a_product_list',
            {templateUrl: 'admin/a_product_list.html'})
});