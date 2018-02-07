var module = angular.module('order', ['summernote']);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_order_create',
            {templateUrl: 'admin/a_order_create.html'})
        .when('/a_order_edit/:orderId',
            {templateUrl: 'admin/a_order_edit.html'})
        .when('/a_order_list',
            {templateUrl: 'admin/a_order_list.html'})
});