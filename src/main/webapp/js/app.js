'use strict';

var app = angular.module('spring-data-rest-sample', ['ui.bootstrap', 'ngResource', 'ngRoute', 'hljs', 'spring-data-rest'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/transactions', {
                templateUrl: 'components/list-transactions.html'
            })
            .when('/accounts', {
                templateUrl: 'components/account/list-accounts.html'
            })
            .when('/categories', {
                templateUrl: 'components/list-categories.html'
            })
            .otherwise({
                templateUrl: 'components/overview.html'
            });
    }])
    .controller('TransactionController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/api/transactions').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
            $scope.transactions = processedResponse._embeddedItems;
        });
    })
    .controller('AccountController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/api/accounts').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
            $scope.accounts = processedResponse._embeddedItems;
        });
    })
    .controller('CategoryController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/api/categories').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
            $scope.categories = processedResponse._embeddedItems;
        });
    });