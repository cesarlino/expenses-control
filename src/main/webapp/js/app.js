'use strict';

var app = angular.module('spring-data-rest-sample', ['ui.bootstrap', 'ngResource', 'ngRoute', 'hljs', 'spring-data-rest'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/transactions', {
                templateUrl: 'components/list-transactions.html'
            })
            .when('/accounts', {
                templateUrl: 'components/list-accounts.html'
            })
            .when('/categories', {
                templateUrl: 'components/list-categories.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    }])
    .controller('TransactionController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/api/transactions').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
            $scope.transactions = processedResponse._embeddedItems;
            $scope.processedResponse = angular.toJson(processedResponse, true);
        });
    })
    .controller('AccountController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/api/accounts').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
            $scope.accounts = processedResponse._embeddedItems;
            $scope.processedResponse = angular.toJson(processedResponse, true);
        });
    })
    .controller('CategoryController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/api/categories').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
            $scope.categories = processedResponse._embeddedItems;
            $scope.processedResponse = angular.toJson(processedResponse, true);
        });
    });