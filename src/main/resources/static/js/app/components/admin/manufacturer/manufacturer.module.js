var module = angular.module('manufacturer', ['summernote']);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_manufacturer_create',
            {templateUrl: 'admin/a_manufacturer_create.html'})
        .when('/a_manufacturer_edit/:manufacturerId',
            {templateUrl: 'admin/a_manufacturer_edit.html'})
        .when('/a_manufacturer_list',
            {templateUrl: 'admin/a_manufacturer_list.html'})
});