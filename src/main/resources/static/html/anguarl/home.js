var app = angular.module('myHome', []).controller('homeCtrl', function ($scope, $http, $q, $window) {

    var user = localStorage.getItem('user');
    if (null != user) {
        var user = typeof user == 'string' ? JSON.parse(user) : user ;
        $scope.user = user;
    }

    $scope.out = function () {
        localStorage.removeItem("user");
        window.location.href = '/login';
    }

});

