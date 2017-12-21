var module = angular.module('article', ['summernote']);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_article_create',
            {templateUrl: 'admin/a_article_create.html'})
        .when('/a_article_edit/:articleId',
            {templateUrl: 'admin/a_article_edit.html'})
        .when('/a_article_list',
            {templateUrl: 'admin/a_article_list.html'})
});