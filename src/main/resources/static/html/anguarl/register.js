var app = angular.module('myReg', []).controller('regCtrl', function ($scope, $http, $q) {

    $scope.entity = {
        account: '',
        email: '',
        password: ''
    };

    $scope.password2 = '';

    var reg_email = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式

    $scope.register = function () {

        if (angular.equals('', $scope.entity.account)) {
            console.log("账号不能为空");
            return;
        }
        ;

        if (angular.equals('', $scope.entity.email)) {
            console.log("邮箱不能为空");
            return;
        }
        ;

        if (!reg_email.test($scope.entity.email)) {
            console.log("邮箱格式输入有误");
            return;
        }
        ;

        console.log($scope.entity.password);
        console.log($scope.password2)
        if (!angular.equals($scope.entity.password, $scope.password2)) {
            console.log("两次输入密码不匹配");
            return;
        }

        var url = '/api/register';
        var date = {
            account: $scope.entity.account,
            email: $scope.entity.email,
            password: $scope.entity.password
        };
        var msg = ajax_http(url, method_post, date);

        if (msg.code =200){
            console.log(msg.message);
            setUser(date);
            href('/home');
        } else {
            console.error(msg.message)
        }

    };

});

