angular.module('article')
    .controller('ArticleEditCtrl', function ($scope, $http, $routeParams) {

        angular.element('#loadingPage').modal('show');

        $scope.article = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $http.get('/admin/article/read/' + $routeParams.articleId)
            .then(function (response) {
                $scope.article = response.data;
                angular.element('#loadingPage').modal('hide');
            });

        $scope.saveArticle = function () {
            $scope.validated = true;
            if ($scope.ruForm.title.$valid) {

                $scope.saving = true;
                $scope.saved = false;
                $scope.notSaved = false;

                $http.post('/admin/article/save', $scope.article)
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

    });