var module = angular.module('product-proposal', []);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/a_product_proposal_create/:productId',
            {templateUrl: 'admin/a_product_proposal_create.html'})
        .when('/a_product_proposal_edit/:productProposalId',
            {templateUrl: 'admin/a_product_proposal_edit.html'})
});