var app = angular.module('myMine', []).controller('mineCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();

    $scope.password1 = '';

    $scope.password2 = '';

    if (!$scope.user) {
        href('/login');
    }

    $scope.init = function () {
        console.log($scope.user);
    }

    $scope.edit = function () {
        var url = '/api/user/edit';
        var msg = ajax_http_post(url, $scope.user);
        console.log(msg);
    }

    $scope.editPwd = function () {
        if (!$scope.password1) {

        }

        if (!$scope.password2) {

        }

        if (!angular.equals($scope.password1, $scope.password2)) {

        }

        var url = '/api/user/editPwd';
        var data = {
            id: $scope.user.id,
            password: $scope.user.password
        }
        var msg = ajax_http_post(url, JSON.stringify(data));
        console.log(msg);
    }
});
