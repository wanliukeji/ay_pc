var app = angular.module('myLogin', []).controller('loginCtrl', function ($scope, $http, $q, $window) {

    $scope.account = 'chenyu';
    $scope.password = '222222';

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
                'account': 'chenyu',
                'password': '222222'
            },
            success: function (res) {
                if (res.code == 200) {
                    console.log(res.message);
                    localStorage.setItem('user', {
                        'account': 'chenyu',
                        'password': '222222'
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

