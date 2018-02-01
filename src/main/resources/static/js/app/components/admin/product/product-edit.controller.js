angular.module('product')
    .controller('ProductEditCtrl', function ($scope, $http, $routeParams, filesUploaderService) {

        angular.element('#loadingPage').modal('show');

        $scope.product = {};
        $scope.productCategoryList = [];
        $scope.manufacturerList = [];

        $scope.selectedToRemove = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $scope.init = function () {
            $http.get('/admin/product/read/' + $routeParams.productId)
                .then(function (response) {
                    $scope.product = response.data;
                    if (response.data.photo) {
                        $scope.photoPrevLinkStyle = {
                            'background-image': 'url(/get_prev_photo/' + response.data.photo.id + ')'
                        };
                    }
                    angular.element('#loadingPage').modal('hide');
                });

            $http.get('/admin/manufacturer/list')
                .then(function (response) {
                    $scope.manufacturerList = response.data;
                });

            $http.get('/admin/product_category/list')
                .then(function (response) {
                    $scope.productCategoryList = response.data;
                });

            // todo wrong! fix
            angular.element('#loadingPage').modal('hide');
        };

        $scope.save = function () {
            $scope.validated = true;
            if ($scope.ruProdNameForm.name.$valid) {

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

        $scope.remove = function () {
            $scope.product.photo = null;
            $scope.photoPrevLink = 'img/noimagefound.jpeg';
        };

        //todo path id insted of object in other places
        $scope.selectToRemove = function (productProposalId) {
            $scope.selectedToRemove = productProposalId;
        };

        $scope.removeProductProposal = function () {
            $http.delete('/admin/product_proposal/delete/' + $scope.selectedToRemove)
                .then(function () {
                    $scope.init();
                });
        };
    });