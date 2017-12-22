angular.module('manufacturer')
    .controller('ManufacturerEditCtrl', function ($scope, $http, $routeParams, filesUploaderService) {

        angular.element('#loadingPage').modal('show');

        $scope.manufacturer = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $http.get('/admin/manufacturer/read/' + $routeParams.manufacturerId)
            .then(function (response) {
                $scope.manufacturer = response.data;
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

                //trying to upload logo first
                uploadImage();
            }
        };

        //todo: make service. in *create replace
        var uploadImage = function () {
            var file = $scope.filesToUpload;
            var uploadUrl = "/upload_photo";

            //uploading logo if user wants
            if (file && file.length !== 0) {

                //getting promiseObject to wait until logo uploads
                var fileUploadedPromise = filesUploaderService.uploadFileToUrl(file, uploadUrl);

                fileUploadedPromise
                    .then(
                        function success(result) {

                            //saving Manufacturer
                            $scope.manufacturer.photo = result.data;
                            saveManufacturer();
                        },
                        function error() {
                            $scope.saving = false;
                            $scope.saved = false;
                            $scope.notSaved = true;
                            //discard logo
                            reset();
                        });
            }
            else {
                saveManufacturer();
            }
        };

        var saveManufacturer = function () {
            $http.post('/admin/manufacturer/save', $scope.manufacturer)
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
            $scope.manufacturer.photo = null;
            $scope.photoPrevLink = 'img/noimagefound.jpeg';
        };
    });