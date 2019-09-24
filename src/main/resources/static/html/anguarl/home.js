var app = angular.module('myHome', []).controller('homeCtrl', function ($scope, $http, $q, $window) {

    var user = localStorage.getItem('user');
    var entity = {};
    if (null != user) {
        // user = JSON.stringify(user);
        var user = typeof user == 'string' ? JSON.parse(user) : user ;
        console.log(user);
    }

});

