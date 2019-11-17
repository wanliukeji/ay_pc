var app = angular.module('myAdmin', []).controller('adminCtrl', function ($scope, $http, $q, $window) {

    $scope.init = function () {
        $scope.stauts = '登录';
    };

    document.onkeydown = function (e) {
        if ((e.keyCode || e.which) == 13) {
            angular.element("#btn").click();
        }
    }

    $scope.submit = function () {
        $('#btn').text('正在登录....');

        var url = '/api/user/admin';
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };

        var msg = ajax_http_post_login(url, date);

        if (msg.code == 200) {
            setUser(date);
            lightyear.notify("登录成功,正在跳转....", 'success', 5000, 'mdi mdi-emoticon-happy', 'top', 'center');
            setTimeout(function () {
                href('/bg/index');
            }, 3000);
        } else {
            lightyear.notify(msg.message, 'danger', 5000, 'mdi mdi-emoticon-happy', 'top', 'center');
            setTimeout(function () {
                history.go(0);
            },3000);
        }
    }
});

