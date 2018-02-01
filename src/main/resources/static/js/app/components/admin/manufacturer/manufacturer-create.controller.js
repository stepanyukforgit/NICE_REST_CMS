angular.module('manufacturer')
    .controller('ManufacturerCtrl', function ($scope, $http, filesUploaderService) {

        $scope.manufacturer = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

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

                            $scope.manufacturer.photo = result.data;
                            saveManufacturer();
                        },
                        function error() {
                            $scope.saving = false;
                            $scope.saved = false;
                            $scope.notSaved = true;
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
    });