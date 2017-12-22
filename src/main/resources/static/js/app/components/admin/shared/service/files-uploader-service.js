angular.module('nicewoodApp')
    .service('filesUploaderService', function ($http) {

        this.uploadFileToUrl = function (files, uploadUrl) {
            var fd = new FormData();
            for (var key in files) {
                fd.append('file', files[key].file);
            }

            return $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            });
        };

        this.resetFiles = function (files, idx) {
            files.splice(idx, 1);
        };
    });