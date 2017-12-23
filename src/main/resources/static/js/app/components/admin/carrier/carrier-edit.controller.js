angular.module('carrier')
    .controller('CarrierEditCtrl', function ($scope, $http, $routeParams, filesUploaderService) {

        angular.element('#loadingPage').modal('show');

        $scope.carrier = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $http.get('/admin/carrier/read/' + $routeParams.carrierId)
            .then(function (response) {
                $scope.carrier = response.data;
                if (response.data.photo) {
                    $scope.photoPrevLinkStyle = {
                        'background-image': 'url(/get_prev_photo/' + response.data.photo.id + ')'
                    };
                }
                angular.element('#loadingPage').modal('hide');
            });

        $scope.save = function () {
            $scope.validated = true;
            if ($scope.ruForm.name.$valid) {

                $scope.saving = true;
                $scope.saved = false;
                $scope.notSaved = false;

                uploadImage();
            }
        };

        var uploadImage = function () {
            var file = $scope.filesToUpload;
            var uploadUrl = "/upload_photo";

            if (file && file.length !== 0) {

                var fileUploadedPromise = filesUploaderService.uploadFileToUrl(file, uploadUrl);

                fileUploadedPromise
                    .then(
                        function success(result) {

                            $scope.carrier.photo = result.data;
                            saveCarrier();
                        },
                        function error() {
                            $scope.saving = false;
                            $scope.saved = false;
                            $scope.notSaved = true;
                            reset();
                        });
            }
            else {
                saveCarrier();
            }
        };

        var saveCarrier = function () {
            $http.post('/admin/carrier/save', $scope.carrier)
                .then(
                    function success() {
                        $scope.saving = false;
                        $scope.notSaved = false;
                        $scope.saved = true;
                    },
                    function error() {
                        $scope.saving = false;
                        $scope.notSaved = true;
                        $scope.saved = false;
                    }
                );
        };

        $scope.reset = function () {
            filesUploaderService.resetFiles($scope.filesToUpload, 0);
        };

        $scope.remove = function () {
            $scope.carrier.photo = null;
            $scope.photoPrevLink = 'img/noimagefound.jpeg';
        };
    });