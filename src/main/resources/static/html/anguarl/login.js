var app = angular.module('myLogin', []).controller('loginCtrl', function ($scope, $http, $q, $window) {

    $scope.submit = function () {
        var url = '/api/login';
        var method = method_post;
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };
        var msg = ajax_http(url, method, date);

        if (msg.code =200){
            console.log(msg.message);
            setUser(date);
            href('/home');
        } else {
            console.error(msg.message)
        }
    }
});

