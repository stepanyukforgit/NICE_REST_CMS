angular.module('article')
    .controller('ArticleListCtrl', function ($scope, $http) {

        angular.element('#loadingPage').modal('show');

        $scope.articleList = [];
        $scope.selectedToRemove = {};

        $scope.init = function () {
            $http.get('/admin/article/list')
                .then(function (response) {
                    $scope.articleList = response.data;
                });
            angular.element('#loadingPage').modal('hide');
        };

        $scope.selectToRemove = function (article) {
            $scope.selectedToRemove = article;
        };

        $scope.removeArticle = function () {
            $http.delete('/admin/article/delete/'+$scope.selectedToRemove.id)
                .then(function () {
                    $scope.init();
                });
        };
    });