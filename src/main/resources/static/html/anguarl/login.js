var app = angular.module('myLogin', []).controller('loginCtrl', function ($scope, $http, $q, $window) {

    $scope.login = function () {
        var url = '/api/user/login';
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };
        var msg = ajax_http_post_login(url, date);
        if (msg.code == 200) {
            msg_success("登录成功,正在进入...")
            setUser(msg.data);

            setTimeout(function () {
                href('/home');
            },3000)

        } else {
            msg_error("账户密码输入不匹配,请重新输入");
        }
    }
});

