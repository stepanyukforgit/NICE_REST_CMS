var module = angular.module('shopinfo', ['summernote']);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_shopinfo',
            {templateUrl: 'admin/a_shopinfo.html'})
});