var app = angular.module('myApp', []);
    app.controller('relCtrl', function ($scope, $http) {
    $scope.info = {
        prv : '上一步',
        next : '下一步'
    }

    $scope.show = function () {
        alert(2);
    }
});