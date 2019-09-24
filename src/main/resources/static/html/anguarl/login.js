var app = angular.module('myLogin', []).controller('loginCtrl', function ($scope, $http, $q, $window) {

    $scope.submit = function () {
        // $http({
        //     url: '/api/login',
        //     method: "POST",
        //     data: {
        //         'account': 'chenyu',
        //         'password': '222222'
        //     }
        // }).success(function (res, status, header, config) {
        //     if (status == 200) {
        //         window.location.href = '/home'
        //         console.log(res);
        //     } else {
        //         console.log(JSON.stringify(status));
        //     }
        // }).error(function (err, status, header, config) {
        //     // window.location.href = '/home'
        //     console.log(err);
        // });

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
                    localStorage.setItem('user', {
                        'account': $scope.account,
                        'password': $scope.password
                    })
                    window.location.href = '/home'
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    }
});

