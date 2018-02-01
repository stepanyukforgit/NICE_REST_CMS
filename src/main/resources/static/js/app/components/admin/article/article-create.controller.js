angular.module('article')
    .controller('ArticleCreateCtrl', function ($scope, $http) {
        $scope.article = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

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