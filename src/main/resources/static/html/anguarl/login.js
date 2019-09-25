var app = angular.module('myLogin', []).controller('loginCtrl', function ($scope, $http, $q, $window) {

    $scope.submit = function () {
        $.ajax({
            url: '/api/login',
            method: 'POST',
            data: {
                'account': $scope.account,
                'password': $scope.password
            },
            success: function (res) {
                if (res.code == 200) {
                    console.log(res.message);
                    localStorage.setItem('user', JSON.stringify({
                        'account': $scope.account,
                        'password': $scope.password
                    }))
                    window.location.href = '/home'
                }
            },
            error: function (err) {
                console.log(err);
            }
        });

        // $http({
        //     url: '/api/login',
        //     method: "POST",
        //     data: {
        //         'account': $scope.account,
        //         'password': $scope.password
        //     },
        //     dataType:'json',
        //     sync:true,
        //     headers: {"Content-Type": "application/json;charset=utf-8"}
        // }).success(function (res, status, header, config) {
        //     if (status == 200) {
        //         $scope.upload_gg();
        //         console.log(config.data);
        //     } else {
        //         console.log(JSON.stringify(status));
        //     }
        // }).error(function (err, status, header, config) {
        //     console.log(JSON.stringify(err, header, config, status));
        // });

    }

});

