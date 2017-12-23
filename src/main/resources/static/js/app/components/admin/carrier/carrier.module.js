var module = angular.module('carrier',[]);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_carrier_create',
            {templateUrl: 'admin/a_carrier_create.html'})
        .when('/a_carrier_edit/:carrierId',
            {templateUrl: 'admin/a_carrier_edit.html'})
        .when('/a_carrier_list',
            {templateUrl: 'admin/a_carrier_list.html'})
});