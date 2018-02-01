angular.module('product-proposal')
    .controller('ProductProposalEditCtrl', function ($scope, $http, $routeParams, filesUploaderService) {

        angular.element('#loadingPage').modal('show');

        $scope.productProposal = {};

        $scope.validated = false;
        $scope.saving = false;
        $scope.saved = false;
        $scope.notSaved = false;

        $scope.init = function () {
            $http.get('/admin/product_proposal/read/' + $routeParams.productProposalId)
                .then(function (response) {
                    $scope.productProposal = response.data;
                    angular.element('#loadingPage').modal('hide');
                });
        };


        $scope.save = function () {
            $scope.validated = true;
            if ($scope.ruProdPropNameForm.name.$valid
                && $scope.prodPropPriceForm.price.$valid) {

                $scope.saving = true;
                $scope.saved = false;
                $scope.notSaved = false;

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
                        $scope.productProposal.photos = result.data;
                        saveProductProposal();
                    },
                    function error() {
                        $scope.saving = false;
                        $scope.saved = false;
                        $scope.notSaved = true;
                        reset();
                    });
            } else {
                saveProductProposal();
            }
        };

        var saveProductProposal = function () {
            $http.post('/admin/product_proposal/save', $scope.productProposal)
                .then(
                    function success() {
                        $scope.saving = false;
                        $scope.notSaved = false;
                        $scope.saved = true;

                        //todo response data?
                        $scope.init();
                    },
                    function error() {
                        $scope.saving = false;
                        $scope.notSaved = true;
                        $scope.saved = false;
                    }
                );
        };

        $scope.remove = function (idx) {
            $scope.productProposal.photos.splice(idx, 1);
        };

        $scope.reset = function (files, idx) {
            filesUploaderService.resetFiles(files, idx);
        };

        $scope.newAttribute = function () {
            $scope.productProposal.attributes.push({});
        };

        $scope.removeAttr = function (attrIdx) {
            $scope.productProposal.attributes.splice(attrIdx, 1);
        };
    });
//todo fix. removing attribute from PP don't remove from DB!