angular.module('product-proposal')
    .controller('ProductProposalCtrl', function ($scope, $http, $routeParams, filesUploaderService) {

        $scope.productProposal = {};
        $scope.productProposal.attributes = [];
        $scope.productId = $routeParams.productId;
        $scope.productName = $routeParams.productName;

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $scope.save = function () {
            $scope.validated = true;
            if ($scope.ruProdPropNameForm.name.$valid
                && $scope.prodPropPriceForm.price.$valid
                && $scope.prodPropVendorForm.vendorCode.$valid) {

                $scope.saving = true;
                $scope.saved = false;
                $scope.notSaved = false;

                uploadImageList();
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
                            $scope.productProposal.photos = result.data;
                            saveProductProposal();
                        },
                        function error() {
                            $scope.saving = false;
                            $scope.saved = false;
                            $scope.notSaved = true;
                            reset();
                        });
            }
            else {
                saveProductProposal();
            }
        };

        var saveProductProposal = function () {
            $http.post('/admin/product_proposal/save/' + $scope.productId, $scope.productProposal)
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

        $scope.newAttribute = function () {
            $scope.productProposal.attributes.push({});
        };

        $scope.removeAttr = function (attrIdx) {
            $scope.productProposal.attributes.splice(attrIdx,1);
        };
    });