var app = angular.module('myAdmin', []).controller('adminCtrl', function ($scope, $http, $q, $window) {

    $scope.submit = function () {
        var url = '/api/admin';
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };
        var msg = ajax_http(url, method_post, date);

        if (msg.code =200){
            console.log(msg.message);
            setUser(date);
            href('/bg/index');
        } else {
            console.error(msg.message)
        }
    }
});

