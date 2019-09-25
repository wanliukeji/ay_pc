var app = angular.module('myHome', []).controller('homeCtrl', function ($scope, $http, $q, $window) {

    $scope.user = getUser();

    $scope.outlogin = function () {
        removeUser("user");
        href('/login');
    }

});

