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

        $.ajax({
            url: '/api/register',
            method: "POST",
            data: {
                account: $scope.entity.account,
                email: $scope.entity.email,
                password: $scope.entity.password
            },
            success: function (res) {
                if (res.code == 200) {
                    console.log(res.message);
                    localStorage.setItem('user', $scope.entity);
                    window.location.href = '/home'
                } else {
                    console.log(res.message);
                }
            },
            error: function (err) {
                console.log(err);
            }
        });


        // $http({
        //     url: '/api/register',
        //     method: "POST",
        //     data: $scope.entity,
        //     headers: {"Content-Type": "application/json;charset=utf-8"}
        // }).success(function (res, status, header, config) {
        //     if (status == 200) {
        //         $scope.upload_gg();
        //         console.log(config.data);
        //     } else {
        //         console.log(JSON.stringify(status));
        //     }
        // }).error(function (err, status, header, config) {
        //     console.log(err);
        // });

    };

});

