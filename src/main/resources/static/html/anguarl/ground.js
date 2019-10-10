var app = angular.module('myGroup', []).controller('groupCtrl', function ($scope, $http, $q) {

    $scope.groupList = [];

    $scope.init = function () {
        $scope.groupList = getSessionObj('groupList');
        $scope.type = getSessionObj('type');
    }

    $scope.goInfo = function (id) {
        var url = 'api/fied/getInfoVo?id=' + id;
        var msg = ajax_http(url, method_get, null);
        if (msg.code = 200) {
            setSessionObj('entity', msg.data);
            href('/info');
        } else {
            history.go(0);
        }
        // setSessionObj()
    }

});

