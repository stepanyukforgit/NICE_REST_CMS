angular.module('product-category')
    .controller('ProductCategoryEditCtrl', function ($scope, $http, $routeParams, filesUploaderService) {

        angular.element('#loadingPage').modal('show');

        $scope.productCategory = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $scope.init = function () {
            $http.get('/admin/product_category/read/' + $routeParams.productCategoryId)
                .then(function (response) {
                    $scope.productCategory = response.data;
                    if (response.data.photo) {
                        $scope.photoPrevLinkStyle = {
                            'background-image': 'url(/get_prev_photo/' + response.data.photo.id + ')'
                        };
                    }
                    angular.element('#loadingPage').modal('hide');
                });
        };

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
            var file = $scope.fileToUpload;

            if (file && file.length !== 0) {
                var uploadUrl = "/upload_photo";
                var fileUploadedPromise = filesUploaderService.uploadFileToUrl(file, uploadUrl);

                fileUploadedPromise.then(
                    function success(result) {
                        $scope.productCategory.photo = result.data;
                        uploadImageList();
                    },
                    function error() {
                        $scope.saving = false;
                        $scope.saved = false;
                        $scope.notSaved = true;
                        reset();
                    });
            } else {
                uploadImageList();
            }
        };

        var uploadImageList = function () {
            var file = $scope.filesToUpload;

            if (file && file.length !== 0) {
                var uploadUrl = "/upload_photo_list";
                var fileUploadedPromise = filesUploaderService.uploadFileToUrl(file, uploadUrl);

                fileUploadedPromise.then(
                    function success(result) {
                        $scope.productCategory.bannerPhotos = result.data;
                        saveProductCategory();
                    },
                    function error() {
                        $scope.saving = false;
                        $scope.saved = false;
                        $scope.notSaved = true;
                    });
            } else {
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

                        $scope.init();
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

        $scope.removePhoto = function () {
            $scope.productCategory.photo = null;
            $scope.photoPrevLink = 'img/noimagefound.jpeg';
        };

        $scope.removeBannerPhotos = function (idx) {
            $scope.productCategory.bannerPhotos.splice(idx, 1);
        };
    });