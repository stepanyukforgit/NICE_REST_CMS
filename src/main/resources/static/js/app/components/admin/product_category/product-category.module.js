var module = angular.module('product-category', []);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_product_category_create',
            {templateUrl: 'admin/a_product_category_create.html'})
        .when('/a_product_category_edit/:productCategoryId',
            {templateUrl: 'admin/a_product_category_edit.html'})
        .when('/a_product_category_list',
            {templateUrl: 'admin/a_product_category_list.html'})
});