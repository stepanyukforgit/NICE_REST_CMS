angular.module('product-category')
    .controller('ProductCategoryCtrl', function ($scope, $http, filesUploaderService) {

        $scope.productCategory = {};

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
                uploadImageList();
            }
        };

        var uploadImage = function () {
            var file = $scope.fileToUpload;
            var uploadUrl = "/upload_photo";

            if (file && file.length !== 0) {

                var fileUploadedPromise = filesUploaderService.uploadFileToUrl(file, uploadUrl);

                fileUploadedPromise
                    .then(
                        function success(result) {
                            $scope.productCategory.photo = result.data;
                        },
                        function error() {
                            $scope.saving = false;
                            $scope.saved = false;
                            $scope.notSaved = true;
                            reset();
                        });
            }
        };

        var uploadImageList = function () {
            var file = $scope.filesToUpload;
            var uploadUrl = "/upload_photo_list";

            if (file && file.length !== 0) {

                var fileUploadedPromise = filesUploaderService.uploadFileToUrl(file, uploadUrl);

                fileUploadedPromise
                    .then(
                        function success(result) {
                            $scope.productCategory.bannerPhotos = result.data;
                            saveProductCategory();
                        },
                        function error() {
                            $scope.saving = false;
                            $scope.saved = false;
                            $scope.notSaved = true;
                            reset();
                        });
            }
            else {
                saveProductCategory();
            }
        };

        var saveProductCategory = function () {
            $http.post('/admin/product_category/save', $scope.productCategory)
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

        $scope.reset = function (files, idx) {
            filesUploaderService.resetFiles(files, idx);
        };
    });












