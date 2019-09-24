var app = angular.module('myLogin', []).controller('loginCtrl', function ($scope, $http, $q) {

    $scope.account = 'chenyu';
    $scope.password = '222222';

    $scope.submit = function () {
        $http({
            url: '/api/login',
            method: "POST",
            data: {
                'account': $scope.account,
                'password': $scope.password
            },
            // headers: {"Content-Type": "application/json;charset=utf-8"}
        }).success(function (res, status, header, config) {
            if (status == 200) {
                console.log(config.data);
            } else {
                console.log(JSON.stringify(status));
            }
        }).error(function (err, status, header, config) {
            console.log(JSON.stringify(err));
        });
    }

});

