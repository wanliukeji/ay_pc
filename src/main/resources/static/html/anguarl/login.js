var app = angular.module('myLogin', []).controller('loginCtrl', function ($scope, $http, $q, $window) {

    $scope.init = function () {
        $scope.stauts = '登录';
    };


    document.onkeydown = function (e) {
        if ((e.keyCode || e.which) == 13) {
            angular.element("#btn").click();
        }
    }

    $scope.login = function () {
        var url = '/api/user/login';
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };

        $scope.stauts = '正在登录.....';

        var msg = ajax_http_post_login(url, date);
        if (msg.code == 200) {
            setUser(msg.data);
            setTimeout(function () {
                href('/home');
            },3000)

        } else {
            msg_error("账户密码输入不匹配,请重新输入");
            setTimeout(function () {
                history.go(0);
            },2000);
        }
    }

});

