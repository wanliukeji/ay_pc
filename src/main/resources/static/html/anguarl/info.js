var app = angular.module('myInfo', []).controller('infoCtrl', function ($scope, $http, $q) {

    $scope.files = [];

    $scope.hoppyItem = [];
    $scope.init = function () {
        $scope.entity = getSessionObj('entity');
        if ($scope.entity == null) {
            href('/login');
        } else {
            getFiles($scope.entity.userId);
            gethoppyItem("加工")
        }
    }

    function getFiles(userId) {
        var url = '/api/file/getInfos?userId=' + userId;
        var msg = ajax_http(url, method_get, null);
        $scope.files = msg.data;
    }

    function gethoppyItem(type) {
        var url = '/api/fied/getVos?type=' + type;
        var msg = ajax_http(url, method_get, null);
        $scope.hoppyItem = msg.data;
    }

    $scope.goInfo = function (id) {
        var url = 'api/fied/getInfoVo?id=' + id;
        var msg = ajax_http(url, method_get, null);
        console.log(msg);
        if (msg.code = 200) {
            $scope.entity = msg.date;
            $scope.entity.company_name = '西部门窗';
        } else {
            history.go(0);
        }
        // setSessionObj()
    }

});

