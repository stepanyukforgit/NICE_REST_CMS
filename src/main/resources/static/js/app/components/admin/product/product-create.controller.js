angular.module('product')
    .controller('ProductCtrl', function ($scope, $http, filesUploaderService) {

        $scope.product = {};
        $scope.productCategoryList = [];
        $scope.manufacturerList = [];

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $http.get('/admin/manufacturer/list')
            .then(function (response) {
                $scope.manufacturerList = response.data;
            });

        $http.get('/admin/product_category/list')
            .then(function (response) {
                $scope.productCategoryList = response.data;
            });

        $scope.save = function () {
            $scope.validated = true;
            if ($scope.ruProdNameForm.name.$valid && $scope.prodCatForm.select.$valid) {

                $scope.saving = true;
                $scope.saved = false;
                $scope.notSaved = false;

                uploadImage();
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
                            $scope.product.photo = result.data;
                            saveProduct();
                        },
                        function error() {
                            $scope.saving = false;
                            $scope.saved = false;
                            $scope.notSaved = true;
                            reset();
                        });
            } else {
                saveProduct();
            }
        };

        var saveProduct = function () {
            $http.post('/admin/product/save', $scope.product)
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