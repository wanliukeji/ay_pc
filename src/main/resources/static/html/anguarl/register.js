var app = angular.module('myReg', []).controller('regCtrl', function ($scope, $http, $q) {

    $scope.entity = {
        account: '',
        email: '',
        password: ''
    };

    $scope.stauts = '注册';

    $scope.password2 = '';

    $scope.register = function () {
        if (!$scope.entity.account) {
            msg_error("账号不能为空");
            return;
        }
        ;

        // if (!$scope.entity.email) {
        //     msg_error("邮箱不能为空");
        //     return;
        // }
        // ;
        //
        // if (!reg_email.test($scope.entity.email)) {
        //     msg_error("邮箱格式输入有误");
        //     return;
        // }
        // ;

        if (!angular.equals($scope.entity.password, $scope.password2)) {
            msg_error("两次输入密码不匹配");
            return;
        }

        var url = '/api/user/register';
        var date = {
            account: $scope.entity.account,
            email: $scope.entity.email,
            password: $scope.entity.password
        };
        var msg = ajax_http_post_login(url, date);

        if (msg.code == 200){
            $scope.stauts = '注册中....';
            setUser(date);
            setTimeout(function () {
                href('/home');
            },3000)
        } else {
            msg_error(msg.msg)
        }

    };

});

